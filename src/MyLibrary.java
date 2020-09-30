import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyLibrary {
    public List<MyBook> listaKsiazek = new ArrayList<MyBook>();
    public int ileKsiazek = listaKsiazek.size();

    public String getData(String list){
        if(list == "short"){
            return getShortListData()+", "+getShortListColumns();
        }
        return getLongListData()+", "+getLongListColumns();
    }
    public Vector<String> getLongListColumns() {

        Vector<String> columns = new Vector<String>();
        columns.add("Id");
        columns.add("Autor");
        columns.add("Tytuł");
        columns.add("Rok");
        columns.add("Kategorie");
        columns.add("Wypożyczona");
        columns.add("Ilość wypożyczeń");

        return columns;
    }
    public Vector<Vector> getLongListData() {
        Vector<Vector> data = new Vector<>();
        for (MyBook book:this.listaKsiazek) {
            Vector<String> row = new Vector<String>();
            row.add(String.valueOf(book.getId()));
            row.add(book.getImionaAutora()+" "+book.getNazwiskoAutora());
            row.add(book.getTytul());
            row.add(String.valueOf(book.getRok()));
            row.add(book.getKategorie());
            row.add(book.getCzyWypozyczona());
            row.add(String.valueOf(book.getLiczbaWypozyczen()));
            data.addElement(row);
        }
        return data;
    }
    public Vector<String> getShortListColumns() {

        Vector<String> columns = new Vector<String>();
        columns.add("Id");
        columns.add("Autor");
        columns.add("Tytuł");
        columns.add("Rok");
        columns.add("Kategorie");
        columns.add("Wypożyczona");

        return columns;
    }

    public Vector<Vector> getShortListData() {
        Vector<Vector> data = new Vector<>();
        for (MyBook book:this.listaKsiazek) {
            Vector<String> row = new Vector<String>();
            row.add(String.valueOf(book.getId()));
            row.add(book.getInicjaly()+book.getNazwiskoAutora());
            row.add(book.getTytul());
            row.add(String.valueOf(book.getRok()));
            row.add(book.getKategorie());
            row.add(book.getCzyWypozyczona());
            data.addElement(row);
        }
        return data;
    }

    public MyBook getBook(int id,String action){
        Boolean alert=true;
        for (MyBook book : listaKsiazek) {
            if (book.getId() == id) {
                alert = false;
                return book;
            }
        }
        if(alert==true)
            JOptionPane.showMessageDialog(null, "Nie ma takiego identyfikatora.");
        return null;
    };

}

