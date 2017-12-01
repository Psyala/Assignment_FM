package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.Match;
import objects.Player;
import objects.Team;
import objects.ValidationObject;
import storage.MatchStorage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MatchForm extends InputForm {
    private Match match;
    private final HashMap<String, Team> teamLookup = new HashMap<>();

    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel matchDatePanel;
    private JLabel matchDateLabel;
    private JTextField matchDateText;
    private JPanel teamPanel;
    private JPanel awayTeamPanel;
    private JPanel homeTeamPanel;
    private JLabel homeTeamLabel;
    private JLabel awayTeamLabel;
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox homeTeamComboBox;
    private JComboBox awayTeamComboBox;

    public MatchForm(Match match) {
        this.match = match;
        super.initialise(match, mainPanel, saveButton, cancelButton);
    }

    public MatchForm(Match match, MatchManagement matchManagement) {
        this.match = match;
        super.initialise(match, mainPanel, null, cancelButton);
        saveButton.addActionListener(e -> {
            saveAction();
            matchManagement.populateMatches();
        });
    }

    public void populateTeams() {
        homeTeamComboBox.removeAllItems();
        awayTeamComboBox.removeAllItems();
        teamLookup.clear();
        List<Team> teamList = FootballManager.teamList;

        for (Team team : teamList) {
            homeTeamComboBox.addItem(team.getTeamName());
            awayTeamComboBox.addItem(team.getTeamName());
            teamLookup.put(team.getTeamName(), team);
        }
    }

    @Override
    protected void initialiseComponents() {
        populateTeams();

        if (match != null) {
            matchDateText.setEnabled(false);
            homeTeamComboBox.setEnabled(false);
            awayTeamComboBox.setEnabled(false);

            matchDateText.setText(FootballManager.formatter.format(match.getDateOfMatch()));
            homeTeamComboBox.setSelectedItem(match.getHomeTeam().getTeamName());
            awayTeamComboBox.setSelectedItem(match.getAwayTeam().getTeamName());
        }
    }

    @Override
    protected String getTitle() {
        if (match == null) {
            return FootballManager.APP_TITLE + " - New Match";
        } else {
            return FootballManager.APP_TITLE + " - " + match.getMatchCode();
        }
    }

    @Override
    protected void saveAction() {
        try {
            if (validateInput().isValid()) {
                if (match == null) {
                    Date dateOfMatch = FootballManager.formatter.parse(matchDateText.getText());
                    Team homeTeam = teamLookup.get(homeTeamComboBox.getSelectedItem());
                    Team awayTeam = teamLookup.get(awayTeamComboBox.getSelectedItem());
                    List<Player> blankPlayersList = new ArrayList<>(); //TODO Needs to be implemented
                    match = new Match(homeTeam, blankPlayersList, awayTeam, blankPlayersList, dateOfMatch);
                    MatchStorage.storeMatch(match);
                } else {
                    MatchStorage.storeMatch(match);
                }
                FootballManager.initialiseData();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        null, "Validation Error: " + validateInput().getValidationMessage(),
                        "Validation Error", JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    protected ValidationObject validateInput() {
        try {
            Date date = FootballManager.formatter.parse(matchDateText.getText());
        } catch (Exception ex) {
            return new ValidationObject(false, "Please enter a valid date (dd/MM/yy)");
        }
        if (homeTeamComboBox.getSelectedItem() == awayTeamComboBox.getSelectedItem()) {
            return new ValidationObject(false, "Home Team cannot be the same as Away team");
        }
        return new ValidationObject(true, "");
    }
}
