import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyLibrary {
    public List<MyBook> listaKsiazek = new ArrayList<MyBook>();
    public int ileKsiazek = listaKsiazek.size();

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

    public void borrowBook(int id){
        for (MyBook book:listaKsiazek){
            if(book.getId()==id){
                book.borrowMe();
                break;
            }
        }
    }
    public void returnBook(int id){
        for (MyBook book:listaKsiazek){
            if(book.getId()==id){
                book.returnMe();
                break;
            }
        }
    }
}
