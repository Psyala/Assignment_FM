package tablemodel;

import objects.Player;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class PlayerTableModel extends TableModel {
    public PlayerTableModel(List<?> data) {
        super(data);
    }

    @Override
    public DefaultTableModel create() {
        DefaultTableModel model = new DefaultTableModel(createTableHeader(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Object o : data) {
            Player player = (Player) o;
            List<Object> row = new ArrayList<>();
            row.add(player.getPlayerCode());
            row.add(player.getFullName());
            model.addRow(createRowObject(row));
        }

        return model;
    }

    @Override
    String[] createTableHeader() {
        return new String[]{"Player Code", "Player Name"};
    }
}
