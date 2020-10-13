import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MyBook implements Comparable<MyBook> {
    private int id, rok, liczbaWypozyczen;
    private boolean czyWypozyczona;
    private String imionaAutora, nazwiskoAutora, tytul, inicjaly;
    private List<String> kategorie;


    public MyBook(int id, String imionaAutora, String nazwiskoAutora, String tytul, int rok, List<String> kategorie,boolean czyWypozyczona, int liczbaWypozyczen) {
            this.id = id;
            this.imionaAutora = imionaAutora;
            this.nazwiskoAutora = nazwiskoAutora;
            this.tytul = tytul;
            this.rok = rok;
            this.kategorie = kategorie;
            this.czyWypozyczona = czyWypozyczona;
            this.liczbaWypozyczen = liczbaWypozyczen;

            if(this.imionaAutora.contains(" ")) {
                String[] imiona = this.imionaAutora.split(" ");
                this.inicjaly = imiona[0].charAt(0) + "." + imiona[1].charAt(0) + ". ";
            }else{
                this.inicjaly = this.imionaAutora.charAt(0) + ". ";
            };

    }

    public int getId() {
        return this.id;
    }

    public String getImionaAutora() {
        return this.imionaAutora;
    }

    public String getInicjaly() {
        return this.inicjaly;
    }

    public String getNazwiskoAutora() {
        return this.nazwiskoAutora;
    }

    public String getTytul() {
        return '"'+this.tytul+'"';
    }

    public int getRok() {
        return this.rok;
    }

    public String getKategorie() {
        String kategorie = "";
        for(String k:this.kategorie){
            if(kategorie == "") kategorie += k;
            else kategorie += ", "+ k;
        }
        return kategorie;
    }

    public String getCzyWypozyczona() {
        if(this.czyWypozyczona == false){
            return "Nie";
        } else{return "Tak";}
    }

    public int getLiczbaWypozyczen() {
        return this.liczbaWypozyczen;
    }
    public static void alert(String bool,String access){
        String message1,message2;
        if(access == "admission") {
            message1 = "Książka została";
            if (bool == "false") {
                message2 = " zwrócona.";
            } else {
                message2 = " wypożyczona.";
            }
        }else {
            message1 = "Ta książka jest już";
            if (bool == "true") {
                message2 = " wypożyczona.";
            } else {
                message2 = " zwrócona.";
            }
        }
        JOptionPane.showMessageDialog(null, message1 + message2);

    }

    public void getMe(String action) {

        if (action == "b") {
            borrowReturnMe("true");
        } else if (action == "r") {
            borrowReturnMe("false");
        } else {
            Main.switchPanel(new AddEditBook(this));
        }
    }

    public void borrowReturnMe(String bool){
        JPanel panel;
        if(bool=="true") panel = new BookId("b","wypożyczona");
        else panel = new BookId("r","zwrócona");

        if(bool == String.valueOf(this.czyWypozyczona)){
            alert(bool,"rejection");
            Main.switchPanel(panel);
        }else {
            Connection conn = Main.DataBase.myConn;
            PreparedStatement preparedStatement = null;
            try {
                if(bool=="false") this.liczbaWypozyczen +=1;
                preparedStatement = conn.prepareStatement(
                        "UPDATE Books " +
                        "SET czyWypozyczona=" + bool +
                        ",liczbaWypozyczen="+this.liczbaWypozyczen +
                        " WHERE id=" + this.id);

                preparedStatement.executeUpdate();
                Main.DataBase.getData();
                alert(bool,"admission");
                Main.switchPanel(panel);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public int compareTo(MyBook book) {
        if(this.getLiczbaWypozyczen() == book.getLiczbaWypozyczen()) return 0;
        else if(this.getLiczbaWypozyczen() > book.getLiczbaWypozyczen()) return -1;
        else return 1;
    }
}
