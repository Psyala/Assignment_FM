package tablemodel;

import objects.Venue;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class VenueTableModel extends TableModel {
    public VenueTableModel(List<Venue> data) {
        super(data);
    }

    @Override
    /*
    Creates a DefaultTableModel with:
    Header & Data: Venue Code, Venue Name
     */
    public DefaultTableModel create() {
        DefaultTableModel model = new DefaultTableModel(createTableHeader(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Object o : data) {
            Venue venue = (Venue) o;
            List<Object> row = new ArrayList<>();
            row.add(venue.getVenueCode());
            row.add(venue.getVenueName());
            model.addRow(createRowObject(row));
        }

        return model;
    }

    @Override
    String[] createTableHeader() {
        return new String[]{"Venue Code", "Venue Name"};
    }
}
