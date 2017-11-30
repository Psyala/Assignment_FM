package tablemodel;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class TableModel {
    protected List<?> data;

    public TableModel(List<?> data) {
        this.data = data;
    }

    public abstract DefaultTableModel create();

    abstract String[] createTableHeader();

    protected Object[] createRowObject(List<Object> row) {
        Object[] rowObject = new Object[row.size()];
        rowObject = row.toArray(rowObject);
        return rowObject;
    }
}
