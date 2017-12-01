package forms;

import main.FootballManager;
import tablemodel.LeagueStatisticsTableModel;
import tablemodel.MatchTableModel;

import javax.swing.*;
import java.util.Arrays;

public class MainForm {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel labelPanel;
    private JPanel managementPanel;
    private JPanel tablePanel;
    private JButton manageLeagueButton;
    private JLabel leagueLabel;
    private JPanel leaugeStatusPanel;
    private JPanel leagueMatchPanel;
    private JLabel leagueStatusLabel;
    private JLabel leagueMatchLabel;
    private JScrollPane leagueStatusScrollPanel;
    private JScrollPane leagueMatchScrollPane;
    private JTable leagueStatusTable;
    private JTable leaugeMatchTable;
    private JButton modifyMatchesButton;

    public MainForm() {
        initialise();
        initialiseComponents();
        manageLeagueButton.addActionListener(e -> new LeagueForm());
        modifyMatchesButton.addActionListener(e -> new MatchManagement());
    }

    private void initialise() {
        frame = new JFrame(FootballManager.APP_TITLE);
        frame.setContentPane(contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void initialiseComponents() {
        leagueLabel.setText(FootballManager.leagueName);
        populateLeagueStatistics();
        populateMatches();
    }

    public void populateLeagueStatistics() {
        leagueStatusTable.setModel(new LeagueStatisticsTableModel(Arrays.asList(FootballManager.leagueObject)).create());
    }

    public void populateMatches() {
        leaugeMatchTable.setModel(new MatchTableModel(FootballManager.matchList).create());
    }
}
