package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.ValidationObject;
import objects.Venue;
import storage.VenueStorage;

import javax.swing.*;

public class VenueForm extends InputForm {
    private Venue venue;

    private JPanel panel1;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel venueNamePanel;
    private JLabel venueNameLabel;
    private JTextField venueNameText;
    private JPanel buttonPanel;
    private JButton saveButton;
    private JButton cancelButton;

    public VenueForm(Venue venue) {
        this.venue = venue;
        super.initialise(venue, mainPanel, saveButton, cancelButton);
    }

    @Override
    protected void initialiseComponents() {
        if (venue != null) {
            venueNameText.setText(venue.getVenueName());
        }
    }

    @Override
    protected String getTitle() {
        if (venue == null) {
            return FootballManager.APP_TITLE + " - New Player";
        } else {
            return FootballManager.APP_TITLE + " - " + venue.getVenueCode();
        }
    }

    @Override
    protected void saveAction() {
        if (validateInput().isValid()) {
            if (venue == null) {
                venue = new Venue(venueNameText.getText());
                VenueStorage.storeVenue(venue);
            } else {
                venue.setVenueName(venueNameText.getText());
                VenueStorage.storeVenue(venue);
            }
            JOptionPane.showMessageDialog(
                    null, "Venue " + venue.getVenueCode() + " saved.", "Success",
                    JOptionPane.INFORMATION_MESSAGE
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
        if (venueNameText.getText().isEmpty()) {
            return new ValidationObject(false, "Venue Name cannot be empty.");
        }
        if (venueNameText.getText().contains("#")) {
            return new ValidationObject(false, "Venue Name cannot contain #.");
        }
        return new ValidationObject(true, "");
    }
}
