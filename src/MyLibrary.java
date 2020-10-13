import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.logging.Logger.global;

public class MyLibrary {
    public List<MyBook> listaKsiazek = new ArrayList<MyBook>();

    public int getNumberOfBooks(){
        return listaKsiazek.size();
    }

    public int getNumberOfBorrowed() {
        int number = 0;
        for (MyBook book:this.listaKsiazek){
            number += book.getLiczbaWypozyczen();
        }
        return number;
    }

    public int getBorrowed() {
        int number = 0;

        for (MyBook book:this.listaKsiazek){
            if(book.getCzyWypozyczona()== "Tak")
                number += 1;
        }
        return number;
    }

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
    public Vector<Vector> getLongListData(List<MyBook> list) {
        Vector<Vector> data = new Vector<>();
        for (MyBook book : list) {
            Vector<String> row = new Vector<String>();
            row.add(String.valueOf(book.getId()));
            row.add(book.getImionaAutora() + " " + book.getNazwiskoAutora());
            row.add(book.getTytul());
            row.add(String.valueOf(book.getRok()));
            row.add(book.getKategorie());
            row.add(book.getCzyWypozyczona());
            row.add(String.valueOf(book.getLiczbaWypozyczen()));
            data.addElement(row);
        }
        return data;
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
    public Vector<Vector> getShortListData(List<MyBook> list) {
        Vector<Vector> data = new Vector<>();
        for (MyBook book:list) {
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

    public List<MyBook> mostPopularBooks(){
        List<MyBook> finalList = new ArrayList<MyBook>();
        List<MyBook> list = this.listaKsiazek;
        Collections.sort(list);

        int counter = 0;
        int lastNumber;
        while(list.size()>4 && counter!=5){
            lastNumber = list.get(counter).getLiczbaWypozyczen();
            if(lastNumber==0){
                counter++;
                break;
            }
            finalList.add(list.get(counter));
            counter++;
        }
        lastNumber = list.get(counter).getLiczbaWypozyczen();
        while (list.get(counter).getLiczbaWypozyczen()==lastNumber && lastNumber!=0)
            finalList.add(list.get(counter));

        return finalList;
    }
    public List<MyBook> mostPopularAutors(){
        List<MyBook> autors = new ArrayList<>();
//        for(MyBook book:this.listaKsiazek){
//            String autor = book.getImionaAutora()+" "+book.getNazwiskoAutora();
//            if(!autors.contains(autor)){
//
//
//            }
//        }
        return autors;
    }
    public List<MyBook> mostPopularBooksOfCategory(){
        List<MyBook> list = new ArrayList<>();

        return list;
    }
}

