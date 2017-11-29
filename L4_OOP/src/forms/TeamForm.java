package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.Team;
import objects.ValidationObject;
import objects.Venue;
import storage.TeamStorage;
import storage.VenueStorage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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

    public TeamForm(Team team) {
        this.team = team;
        super.initialise(team, mainPanel, saveButton, cancelButton);
        addVenueButton.addActionListener(e -> {
            new VenueForm(null);
            populateVenues();
        });
    }

    private void populateVenues() {
        venueComboBox.removeAllItems();
        venueLookup.clear();
        List<Venue> venues = VenueStorage.retrieveVenues();

        for (Venue venue : venues) {
            venueComboBox.addItem(venue.getVenueName());
            venueLookup.put(venue.getVenueName(), venue);
        }
    }

    @Override
    protected void initialiseComponents() {
        populateVenues();
        if (team != null) {
            teamNameText.setEnabled(false);

            teamNameText.setText(team.getTeamName());
            venueComboBox.setSelectedItem(team.getVenue().getVenueName());
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
                //TODO: get team player list
                TeamStorage.storeTeam(team);
            } else {
                team.setVenue(venueLookup.get(venueComboBox.getSelectedItem()));
                //TODO: get team player list
                TeamStorage.storeTeam(team);
            }
            JOptionPane.showMessageDialog(null, "Team " + team.getTeamCode() + " saved,",
                    "Success", JOptionPane.INFORMATION_MESSAGE
            );
            FootballManager.populateLists();
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
        return new ValidationObject(true, "");
    }
}
