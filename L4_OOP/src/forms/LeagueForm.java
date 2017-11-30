package forms;

import forms.base.InputForm;
import objects.ValidationObject;

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

    public LeagueForm() {
        super.initialise(null, contentPanel, null, null);
    }

    @Override
    protected void initialiseComponents() {

    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected void saveAction() {

    }

    @Override
    protected ValidationObject validateInput() {
        return null;
    }
}
