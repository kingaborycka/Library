import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MyBook {
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
    public void borrowMe(String bool) {
        Connection conn = Main.DataBase.myConn;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("UPDATE Books SET czyWypozyczona="+bool+ " WHERE id="+this.id);
            preparedStatement.executeUpdate();
            Main.DataBase.getData();
            System.out.println(this.czyWypozyczona);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
