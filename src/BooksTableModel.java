import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.util.List;

public class BooksTableModel extends AbstractTableModel {
    private static final int COLUMN_WYPOZYCZENIA = 0;
    private static final int COLUMN_NAZWISKO     = 1;
    private static final int COLUMN_ROK          = 2;
    private static final int COLUMN_TYTUŁ        = 3;

    private List<MyBook> booksList;
    private Vector<String> columnNames;

    public BooksTableModel(List<MyBook> bookList, Vector<String> columnNames){
        this.booksList = bookList;
        this.columnNames = columnNames;
    }


    @Override
    public int getRowCount() {
        return booksList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (booksList.isEmpty()){
            return Object.class;
        }
        return getValueAt(0,columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MyBook book = booksList.get(rowIndex);
        Object returnValue = null;

        switch (columnIndex) {
            case COLUMN_WYPOZYCZENIA:
                returnValue = book.getLiczbaWypozyczen();
                break;
            case COLUMN_NAZWISKO:
                returnValue = book.getNazwiskoAutora();
                break;
            case COLUMN_ROK:
                returnValue = book.getRok();
                break;
            case COLUMN_TYTUŁ:
                returnValue = book.getTytul();
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
        return returnValue;
    }
    
}
