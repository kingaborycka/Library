import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BooksTableModel extends AbstractTableModel {
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_AUTOR = 1;
    private static final int COLUMN_TYTUŁ = 2;
    private static final int COLUMN_ROK = 3;
    private static final int COLUMN_KATEGORIE = 4;
    private static final int COLUMN_WYPOZYCZONA = 5;
    private static final int COLUMN_WYPOZYCZENIA = 6;



    private List<MyBook> booksList;
    private String[] columnNames;
    public String list;

    public BooksTableModel(List<MyBook> booksList, String[] columnNames,String list) {
        this.booksList = booksList;
        this.columnNames = columnNames;
        this.list = list;

    }


    @Override
    public int getRowCount() {
        return booksList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (booksList.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MyBook book = booksList.get(rowIndex);
        Object returnValue = null;

        switch (columnIndex) {
            case COLUMN_ID:
                returnValue = book.getId();
                break;
            case COLUMN_KATEGORIE:
                returnValue = book.getKategorie();
                break;
            case COLUMN_WYPOZYCZONA:
                returnValue = book.getCzyWypozyczona();
                break;
            case COLUMN_WYPOZYCZENIA:
                returnValue = book.getLiczbaWypozyczen();
                break;
            case COLUMN_AUTOR:
                if(this.list.equals("short"))
                    returnValue = book.getInicjaly()+book.getNazwiskoAutora();
                else
                    returnValue = book.getImionaAutora()+" "+book.getNazwiskoAutora();
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

    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 35; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300)
                width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}