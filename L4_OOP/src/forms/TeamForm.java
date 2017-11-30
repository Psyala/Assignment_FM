package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.Player;
import objects.Team;
import objects.ValidationObject;
import objects.Venue;
import storage.PlayerStorage;
import storage.TeamStorage;
import storage.VenueStorage;
import tablemodel.PlayerTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamForm extends InputForm {

    private final HashMap<String, Venue> venueLookup = new HashMap<>();
    private Team team;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel teamNamePanel;
    private JLabel teamNameLabel;
    private JTextField teamNameText;
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel venuePanel;
    private JLabel venueLabel;
    private JButton addButton;
    private JButton removeButton;
    private JPanel tableButtonPanel;
    private JPanel playerListPanel;
    private JPanel teamPlayerListPanel;
    private JLabel playerListLabel;
    private JLabel teamPlayerListLabel;
    private JButton addVenueButton;
    private JComboBox venueComboBox;
    private JScrollPane playerListScrollPane;
    private JScrollPane teamPlayerListScrollPane;
    private JTable playerListTable;
    private JTable teamPlayerListTable;

    public TeamForm(Team team) {
        this.team = team;
        super.initialise(team, contentPanel, saveButton, cancelButton);
        addVenueButton.addActionListener(e -> new VenueForm(null, this));
        addButton.addActionListener(e -> moveSelectedPlayer(playerListTable, teamPlayerListTable));
        removeButton.addActionListener(e -> moveSelectedPlayer(teamPlayerListTable, playerListTable));
    }

    public TeamForm(Team team, LeagueForm leagueForm) {
        this.team = team;
        super.initialise(team, contentPanel, null, cancelButton);
        addVenueButton.addActionListener(e -> new VenueForm(null, this));
        addButton.addActionListener(e -> moveSelectedPlayer(playerListTable, teamPlayerListTable));
        removeButton.addActionListener(e -> moveSelectedPlayer(teamPlayerListTable, playerListTable));
        saveButton.addActionListener(e -> {
            saveAction();
            leagueForm.populateTeams();
        });
    }

    private void moveSelectedPlayer(JTable fromTable, JTable toTable) {
        int selectedRow = fromTable.getSelectedRow();
        if (selectedRow >= 0) {
            String playerCode = (String) fromTable.getValueAt(selectedRow, 0);
            String playerName = (String) fromTable.getValueAt(selectedRow, 1);
            String injured = String.valueOf(fromTable.getValueAt(selectedRow, 2));
            ((DefaultTableModel) fromTable.getModel()).removeRow(selectedRow);
            ((DefaultTableModel) toTable.getModel()).addRow(new Object[]{playerCode, playerName, injured});
        } else {
            JOptionPane.showMessageDialog(null, "No table row selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void populateVenues() {
        venueComboBox.removeAllItems();
        venueLookup.clear();
        List<Venue> venues = VenueStorage.retrieveVenues();

        for (Venue venue : venues) {
            venueComboBox.addItem(venue.getVenueName());
            venueLookup.put(venue.getVenueName(), venue);
        }
    }

    private void populateFreeAgents() {
        List<Player> freeAgents = new ArrayList<>();
        for (Player player : FootballManager.playerList) {
            boolean free = true;
            for (Team team : FootballManager.teamList) {
                for (Player teamPlayer : team.getPlayers()) {
                    if (teamPlayer.getPlayerCode().equals(player.getPlayerCode())) {
                        free = false;
                    }
                }
            }
            if (free) {
                freeAgents.add(player);
            }
        }
        playerListTable.setModel(new PlayerTableModel(freeAgents).create());
    }

    private List<Player> getTeamPlayers() {
        List<Player> players = new ArrayList<>();
        for (int row = 0; row < teamPlayerListTable.getRowCount(); row++) {
            String playerCode = (String) teamPlayerListTable.getValueAt(row, 0);
            players.add(PlayerStorage.getPlayer(playerCode));
        }
        return players;
    }

    @Override
    protected void initialiseComponents() {
        populateVenues();
        populateFreeAgents();

        if (team != null) {
            teamNameText.setEnabled(false);

            teamNameText.setText(team.getTeamName());
            venueComboBox.setSelectedItem(team.getVenue().getVenueName());
            teamPlayerListTable.setModel(new PlayerTableModel(team.getPlayers()).create());
        } else {
            teamPlayerListTable.setModel(new PlayerTableModel(new ArrayList<>()).create());
        }
    }

    @Override
    protected String getTitle() {
        if (team == null) {
            return FootballManager.APP_TITLE + " - New Team";
        } else {
            return FootballManager.APP_TITLE + " - " + team.getTeamCode();
        }
    }

    @Override
    protected void saveAction() {
        if (validateInput().isValid()) {
            if (team == null) {
                team = new Team(teamNameText.getText());
                team.setVenue(venueLookup.get(venueComboBox.getSelectedItem()));
                team.setPlayers(getTeamPlayers());
                TeamStorage.storeTeam(team);
            } else {
                team.setVenue(venueLookup.get(venueComboBox.getSelectedItem()));
                team.setPlayers(getTeamPlayers());
                TeamStorage.storeTeam(team);
            }
            JOptionPane.showMessageDialog(null, "Team " + team.getTeamCode() + " saved,",
                    "Success", JOptionPane.INFORMATION_MESSAGE
            );
            FootballManager.initialiseData();
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(
                    null, "Validation Error: " + validateInput().getValidationMessage(),
                    "Validation Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    protected ValidationObject validateInput() {
        if (getTeamPlayers().size() > 21) {
            return new ValidationObject(false, "A team cannot have more than 21 players.");
        }
        if (teamNameText.getText().isEmpty()) {
            return new ValidationObject(false, "The team name cannot be empty.");
        }
        if (teamNameText.getText().contains("#")) {
            return new ValidationObject(false, "Team name cannot contain #.");
        }
        if (venueComboBox.getSelectedItem() == null) {
            return new ValidationObject(false, "Team must have a Venue.");
        }
        return new ValidationObject(true, "");
    }
}
