package tablemodel;

import objects.League;
import objects.Team;
import storage.TeamStorage;

import javax.swing.table.DefaultTableModel;
import java.util.*;

public class LeagueStatisticsTableModel extends TableModel {
    public LeagueStatisticsTableModel(List<League> data) {
        super(data);

    }

    @Override
    /*
    Creates a DefaultTableModel with:
    Header & Data: Position, Team Name, Points
     */
    public DefaultTableModel create() {
        DefaultTableModel model = new DefaultTableModel(createTableHeader(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        League league = (League) data.get(0);
        int position = 1;
        HashMap<String, League.LeagueTeamInformation> leagueTeamInformationHashMap = league.getLeagueTeamInformation();

        //Puts the HashMap in a TreeMap so that it can be sorted
        TreeMap<String, String> sortedPoints = new TreeMap<>();
        for (Map.Entry<String, League.LeagueTeamInformation> entry : leagueTeamInformationHashMap.entrySet()) {
            sortedPoints.put(entry.getValue().getPoints() * -1 + "#" + entry.getKey(), entry.getKey());
        }

        for (Map.Entry<String, String> teamInformation : sortedPoints.entrySet()) {
            List<Object> row = new ArrayList<>();
            Team team = TeamStorage.getTeam(teamInformation.getValue());
            int points = Integer.valueOf(teamInformation.getKey().split("#")[0]) * -1;
            row.add(position);
            row.add(team.getTeamName());
            row.add(points);
            model.addRow(createRowObject(row));

            position++;
        }

        return model;
    }

    @Override
    String[] createTableHeader() {
        return new String[]{"Position", "Team Name", "Points"};
    }
}
