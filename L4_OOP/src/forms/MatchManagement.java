package forms;

import forms.base.InputForm;
import main.FootballManager;
import objects.ValidationObject;
import storage.MatchStorage;
import tablemodel.MatchTableModel;

import javax.swing.*;

public class MatchManagement extends InputForm {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JLabel matchesLabel;
    private JScrollPane matchesScrollPane;
    private JTable matchesTable;
    private JPanel buttonPanel;
    private JButton newMatchButton;
    private JButton editMatchButton;
    private JButton resolveMatchButton;

    public MatchManagement() {
        super.initialise(null, mainPanel, null, null);
        newMatchButton.addActionListener(e -> new MatchForm(null, this));
        editMatchButton.addActionListener(e -> {
            int selectedRow = matchesTable.getSelectedRow();
            if (selectedRow >= 0) {
                String matchCode = (String) matchesTable.getValueAt(selectedRow, 0);
                new MatchForm(MatchStorage.getMatch(matchCode), this);
            } else {
                JOptionPane.showMessageDialog(null, "Select a match to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        resolveMatchButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Not yet implemented.", "Warning", JOptionPane.WARNING_MESSAGE));
    }

    public void populateMatches() {
        matchesTable.setModel(new MatchTableModel(FootballManager.matchList, true).create());
    }

    @Override
    protected void initialiseComponents() {
        populateMatches();
    }

    @Override
    protected String getTitle() {
        return FootballManager.APP_TITLE + " - Match Management";
    }

    @Override
    protected void saveAction() {

    }

    @Override
    protected ValidationObject validateInput() {
        return null;
    }
}
