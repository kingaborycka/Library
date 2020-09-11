import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ArticleDAO {
    Statement myStmt;
    Connection myConn;
    public MyLibrary Library;

    public void connect() {
        try{
            //Get a connection to databasephp
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/LibraryBase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "In4matyk@");
            //Create a statement
            myStmt = myConn.createStatement();

        }catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void getData() {
        connect();
        try{
            Library = new MyLibrary();

            ResultSet myRs = myStmt.executeQuery("select * from Books");
            while (myRs.next()) {
                String[] year = myRs.getString("rok").split("-");
                MyBook book = new MyBook(
                        Integer.parseInt(myRs.getString("id")),
                        myRs.getString("imionaAutora"),
                        myRs.getString("nazwiskoAutora"),
                        myRs.getString("tytul"),
                        Integer.parseInt(year[0]),
                        List.of(myRs.getString("kategorie").split(",")),
                        myRs.getBoolean("czyWypozyczona"),
                        Integer.parseInt(myRs.getString("liczbaWypozyczen"))
                );
                Library.listaKsiazek.add(book);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    };
}
