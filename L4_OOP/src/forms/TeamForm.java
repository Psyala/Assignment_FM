package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.Team;
import objects.ValidationObject;

import javax.swing.*;

public class TeamForm extends InputForm {

    private final Team team;
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

    public TeamForm(Team team) {
        this.team = team;

        //super.initialise(team, mainPanel, saveButton, cancelButton);
    }

    @Override
    protected void initialiseComponents() {

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

    }

    @Override
    protected ValidationObject validateInput() {
        return new ValidationObject(true, "");
    }
}
