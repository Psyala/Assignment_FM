package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.ValidationObject;
import storage.LeagueStorage;

import javax.swing.*;

public class NewLeagueForm extends InputForm {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel leagueNamePanel;
    private JLabel leagueNameLabel;
    private JTextField leagueNameText;
    private JPanel buttonPanel;
    private JButton saveButton;

    public NewLeagueForm() {
        super.initialise(null, mainPanel, saveButton, null);
    }

    @Override
    protected void initialiseComponents() {

    }

    @Override
    protected String getTitle() {
        return FootballManager.APP_TITLE + " - New League";
    }

    @Override
    protected void saveAction() {
        if (validateInput().isValid()) {
            LeagueStorage.setLeagueName(leagueNameText.getText());
            FootballManager.load();
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Validation Error: " + validateInput().getValidationMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected ValidationObject validateInput() {
        if (leagueNameText.getText().isEmpty()) {
            return new ValidationObject(false, "League Name cannot be empty.");
        }
        return new ValidationObject(true, "");
    }
}
