package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.Player;
import objects.ValidationObject;
import storage.PlayerStorage;

import javax.swing.*;

public class PlayerForm extends InputForm {
    private Player player;

    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel firstNamePanel;
    private JLabel firstNameLabel;
    private JTextField firstNameText;
    private JPanel lastNamePanel;
    private JLabel lastNameLabel;
    private JLabel injuredLabel;
    private JPanel injuredPanel;
    private JCheckBox injuredCheckBox;
    private JTextField lastNameText;
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton cancelButton;

    public PlayerForm(Player player) {
        this.player = player;
        super.initialise(player, mainPanel, saveButton, cancelButton);
    }

    public PlayerForm(Player player, LeagueForm leagueForm) {
        this.player = player;
        super.initialise(player, mainPanel, null, cancelButton);
        saveButton.addActionListener(e -> {
            saveAction();
            leagueForm.populatePlayers();
        });
    }

    @Override
    protected void initialiseComponents() {
        if (player != null) {
            firstNameText.setEnabled(false);
            lastNameText.setEnabled(false);
            firstNameText.setText(player.getFirstName());
            lastNameText.setText(player.getLastName());
            injuredCheckBox.setSelected(player.getInjured());
        }
    }

    @Override
    protected String getTitle() {
        if (player == null) {
            return FootballManager.APP_TITLE + " - New Player";
        } else {
            return FootballManager.APP_TITLE + " - " + player.getPlayerCode();
        }
    }

    @Override
    protected void saveAction() {
        if (validateInput().isValid()) {
            if (player == null) {
                player = new Player(firstNameText.getText(), lastNameText.getText());
                player.setInjured(injuredCheckBox.isSelected());
                PlayerStorage.storePlayer(player);
            } else {
                player.setInjured(injuredCheckBox.isSelected());
                PlayerStorage.storePlayer(player);
            }
            JOptionPane.showMessageDialog(
                    null, "Player " + player.getPlayerCode() + " saved.", "Success",
                    JOptionPane.INFORMATION_MESSAGE
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
        if (firstNameText.getText().isEmpty()) {
            return new ValidationObject(false, "A first name must be provided.");
        }
        if (lastNameText.getText().isEmpty()) {
            return new ValidationObject(false, "A last name must be provided.");
        }
        if (firstNameText.getText().contains("#")) {
            return new ValidationObject(false, "First name cannot contain #.");
        }
        if (lastNameText.getText().contains("#")) {
            return new ValidationObject(false, "Last name cannot contain #.");
        }
        return new ValidationObject(true, "");
    }
}
