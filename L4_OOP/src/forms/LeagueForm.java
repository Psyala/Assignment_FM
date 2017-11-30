package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.ValidationObject;
import storage.PlayerStorage;
import storage.TeamStorage;
import storage.VenueStorage;
import tablemodel.PlayerTableModel;
import tablemodel.TeamTableModel;
import tablemodel.VenueTableModel;

import javax.swing.*;

public class LeagueForm extends InputForm {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel middlePanel;
    private JLabel playersLabel;
    private JLabel teamsLabel;
    private JLabel venuesLabel;
    private JScrollPane playersScrollPane;
    private JScrollPane venuesScrollPane;
    private JScrollPane teamsScrollPane;
    private JPanel playersButtonPanel;
    private JButton playersNewButton;
    private JButton playersEditButton;
    private JPanel teamsButtonPanel;
    private JButton teamsNewButton;
    private JButton teamsEditButton;
    private JPanel venuesButtonPanel;
    private JButton venuesNewButton;
    private JButton venuesEditButton;
    private JTable playersTable;
    private JTable teamsTable;
    private JTable venuesTable;

    public LeagueForm() {
        super.initialise(null, contentPanel, null, null);
        playersNewButton.addActionListener(e -> new PlayerForm(null, this));
        playersEditButton.addActionListener(e -> {
            int selectedRow = playersTable.getSelectedRow();
            if (selectedRow >= 0) {
                String playerCode = (String) playersTable.getValueAt(selectedRow, 0);
                new PlayerForm(PlayerStorage.getPlayer(playerCode), this);
            } else {
                JOptionPane.showMessageDialog(null, "Select a player to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        teamsNewButton.addActionListener(e -> new TeamForm(null, this));
        teamsEditButton.addActionListener(e -> {
            int selectedRow = teamsTable.getSelectedRow();
            if (selectedRow >= 0) {
                String teamCode = (String) teamsTable.getValueAt(selectedRow, 0);
                new TeamForm(TeamStorage.getTeam(teamCode), this);
            } else {
                JOptionPane.showMessageDialog(null, "Select a team to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        venuesNewButton.addActionListener(e -> new VenueForm(null, this));
        venuesEditButton.addActionListener(e -> {
            int selectedRow = venuesTable.getSelectedRow();
            if (selectedRow >= 0) {
                String venueCode = (String) venuesTable.getValueAt(selectedRow, 0);
                new VenueForm(VenueStorage.getVenue(venueCode), this);
            } else {
                JOptionPane.showMessageDialog(null, "Select a venue to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    void populatePlayers() {
        playersTable.setModel(new PlayerTableModel(FootballManager.playerList).create());
    }

    void populateTeams() {
        teamsTable.setModel(new TeamTableModel(FootballManager.teamList).create());
    }

    void populateVenues() {
        venuesTable.setModel(new VenueTableModel(FootballManager.venueList).create());
    }

    @Override
    protected void initialiseComponents() {
        populatePlayers();
        populateTeams();
        populateVenues();
    }

    @Override
    protected String getTitle() {
        return FootballManager.APP_TITLE + " - Modify League";
    }

    @Override
    protected void saveAction() {

    }

    @Override
    protected ValidationObject validateInput() {
        return null;
    }
}
