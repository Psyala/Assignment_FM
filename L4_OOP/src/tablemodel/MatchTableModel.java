package tablemodel;

import main.FootballManager;
import objects.Match;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchTableModel extends TableModel {
    private final boolean viewOld;

    public MatchTableModel(List<Match> data) {
        super(data);
        viewOld = false;
    }

    public MatchTableModel(List<Match> data, boolean viewOld) {
        super(data);
        this.viewOld = viewOld;
    }

    @Override
    /*
    Creates a DefaultTableModel with:
    Header & Data: Match Code, Date, Venue, Home Team, Away Team
     */
    public DefaultTableModel create() {
        DefaultTableModel model = new DefaultTableModel(createTableHeader(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Object o : data) {
            Match match = (Match) o;

            boolean insertRow = false;
            Date dateOfMatch = match.getDateOfMatch();
            if (dateOfMatch.compareTo(new Date()) >= 0) {
                insertRow = true;
            }

            if (insertRow || viewOld) {
                List<Object> row = new ArrayList<>();
                row.add(match.getMatchCode());
                row.add(FootballManager.formatter.format(dateOfMatch));
                row.add(match.getVenue().getVenueName());
                row.add(match.getHomeTeam().getTeamName());
                row.add(match.getAwayTeam().getTeamName());
                model.addRow(createRowObject(row));
            }
        }

        return model;
    }

    @Override
    String[] createTableHeader() {
        return new String[]{"Match Code", "Date", "Venue", "Home Team", "Away Team"};
    }
}
