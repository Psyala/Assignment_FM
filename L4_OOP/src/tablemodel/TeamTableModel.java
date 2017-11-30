package tablemodel;

import objects.Team;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TeamTableModel extends TableModel {

    public TeamTableModel(List<Team> data) {
        super(data);
    }

    @Override
    /*
    Creates a DefaultTableModel with:
    Header & Data: Team Code, Team Name
     */
    public DefaultTableModel create() {
        DefaultTableModel model = new DefaultTableModel(createTableHeader(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Object o : data) {
            Team team = (Team) o;
            List<Object> row = new ArrayList<>();
            row.add(team.getTeamCode());
            row.add(team.getTeamName());
            model.addRow(createRowObject(row));
        }

        return model;
    }

    @Override
    String[] createTableHeader() {
        return new String[]{"Team Code", "Team Name"};
    }
}
