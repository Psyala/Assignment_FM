package tablemodel;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class TableModel {
    List<?> data;

    TableModel(List<?> data) {
        this.data = data;
    }

    //Creates DefaultTableModel for the TableModel type - with header and data
    public abstract DefaultTableModel create();

    //Returns String array to be used as the header in the DefaultTableModel
    abstract String[] createTableHeader();

    //Converst a List to an array to use in DefaultTableModel to add adata
    Object[] createRowObject(List<Object> row) {
        Object[] rowObject = new Object[row.size()];
        rowObject = row.toArray(rowObject);
        return rowObject;
    }
}
