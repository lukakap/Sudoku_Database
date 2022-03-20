import javax.swing.table.AbstractTableModel;
import java.util.*;

public class MetropolisTableModel extends AbstractTableModel {

    private String[] header = {"Metropolis", "Continent","Population"};
    MetropolisStore store;

    public MetropolisTableModel(MetropolisStore store){
        this.store = store;
    }

    /**
     * Returns the number of rows in the model.
     * A JTable uses this method to determine how many rows it should display.
     * This method should be quick, as it is called frequently during rendering.
     * @return the number of rows in the model
     */

    @Override
    public int getRowCount() {
        return store.getMetropolises().size();
    }

    /**
     * Returns the number of columns in the model.
     * A JTable uses this method to determine how many
     * columns it should create and display by default.
     * @return the number of columns in the model
     */

    @Override
    public int getColumnCount() {
        return header.length;
    }

    /**
     *Returns the name of the column at columnIndex.
     * This is used to initialize the table's column header name. Note:
     * this name does not need to be unique; two columns in a table can have the same name.
     * @param column the index of the column
     * @return the name of the column
     */

    @Override
    public String getColumnName(int column){
        return header[column];
    }

    /**
     * Returns true if the cell at rowIndex and columnIndex is editable.
     * Otherwise, setValueAt on the cell will not change the value of that cell.
     * Parameters:
     * @param rowIndex the row whose value to be queried
     * @param columnIndex the column whose value to be queried
     * @return true if the cell is editable
     */

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }

    /**
     * Returns the value for the cell at columnIndex and rowIndex.
     * @param rowIndex the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Metropolis> Metropolises = store.getMetropolises();
        Metropolis metropolis = Metropolises.get(rowIndex);
        switch (columnIndex){
            case 0: return metropolis.getMetropolisName();
            case 1: return metropolis.getContinent();
            case 2: return metropolis.getPopulation();
        }
        throw new RuntimeException("Couldn't get value!!");
    }

    /**
     * set new list of metropolises. And message table model about changes in DATA.
     * @param Metropolises new list of metropolises
     */

    public void setMetropolises(List<Metropolis> Metropolises){
        store.setList(Metropolises);
        fireTableDataChanged();
    }
}
