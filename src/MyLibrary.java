import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyLibrary {
    public List<MyBook> listaKsiazek = new ArrayList<>();

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
            if(book.getCzyWypozyczona().equals("Tak")) {
                number += 1;
            }
        }
        return number;
    }

//    public String getData(String list){
//        if(list.equals("short")){
//            return getShortListData()+", "+getShortListColumns();
//        }
//        return getLongListData()+", "+getLongListColumns();
//    }
    public String[] getLongListColumns() {
        String[] columns = {"Id", "Autor", "Tytuł", "Rok", "Kategorie", "Wypożyczona","Ilość wypożyczeń"};
        return columns;
    }

    public String[] getShortListColumns() {
        String[] columns = {"Id", "Autor", "Tytuł", "Rok", "Kategorie", "Wypożyczona"};
        return columns;
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

    public List<Map.Entry<String,MyBook>> mostPopularAutors(){
        Map<String,MyBook> autors = new HashMap<>();
        for(MyBook book:this.listaKsiazek){
            String autor = book.getImionaAutora() + " " + book.getNazwiskoAutora();
            if(!autors.containsKey(autor)) {
                autors.put(autor,book);
            }else if(autors.get(autor).getLiczbaWypozyczen()<book.getLiczbaWypozyczen()) {
                autors.put(autor, book);
            }
        }

        List<Map.Entry<String,MyBook>> entryList = new ArrayList<>(autors.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, MyBook>>() {
            @Override
            public int compare(Map.Entry<String, MyBook> o1, Map.Entry<String, MyBook> o2) {
                return o1.getValue().compareTo(o2.getValue());

            }
        });

        return entryList.subList(0,5);
    }
    public List<MyBook> searcher(String pattern,String kategoria){
        List<MyBook> finalList = new ArrayList<MyBook>();
        int scenariusz;

        if(kategoria == "nazwisko autora")
            scenariusz = 1;
        else if(kategoria == "kategorię")
            scenariusz = 2;
        else scenariusz = 3;

        Pattern compiledPattern = Pattern.compile(pattern.toLowerCase().strip());

        for(MyBook book:this.listaKsiazek){
            switch(scenariusz){
                case 1:
                    if(compiledPattern.matcher(book.getNazwiskoAutora().toLowerCase()).find()){
                        finalList.add(book);
                    }
                case 2:
                    if(compiledPattern.matcher(book.getKategorie()).find()){
                        finalList.add(book);
                    };
                case 3:
                    if(compiledPattern.matcher(book.getTytul().toLowerCase()).find()){
                        finalList.add(book);
                    }
            }
        }

        return finalList;
    }
}

