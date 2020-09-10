import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class AddBook extends JPanel implements ActionListener{
    JButton bMenu, bAdd;
    JTextField tytulField, nazwiskoAutoraField, imionaAutoraField, rokField;
    JCheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9;
    ArrayList<JCheckBox> checkBoxes;
    public AddBook() {

        tytulField = new JTextField("Tyt", 10);
        this.add(tytulField);

        nazwiskoAutoraField = new JTextField("Nazwisko", 10);
        this.add(nazwiskoAutoraField);

        imionaAutoraField = new JTextField("Imiona", 10);
        this.add(imionaAutoraField);

        rokField = new JTextField("2000", 10);
        this.add(rokField);

//Można stworzyć klasę, myCheckBox, żeby nie powtarzać tyle kodu

        checkBoxes = new ArrayList<>();

        checkBox1 = new JCheckBox("literatura piękna", false);
        checkBox1.setBounds(100,150, 50,50);
        this.add(checkBox1);
        checkBoxes.add(checkBox1);
        checkBox2 = new JCheckBox("romans", false);
        checkBox2.setBounds(100,150, 50,50);
        this.add(checkBox2);
        checkBoxes.add(checkBox2);
        checkBox3 = new JCheckBox("powieść historyczna", false);
        checkBox3.setBounds(100,150, 50,50);
        this.add(checkBox3);
        checkBoxes.add(checkBox3);
        checkBox4 = new JCheckBox("fantasy", false);
        checkBox4.setBounds(100,150, 50,50);
        this.add(checkBox4);
        checkBoxes.add(checkBox4);
        checkBox5 = new JCheckBox("obyczajowa", false);
        checkBox5.setBounds(100,150, 50,50);
        this.add(checkBox5);
        checkBoxes.add(checkBox5);
        checkBox6 = new JCheckBox("kryminał", false);
        checkBox6.setBounds(100,150, 50,50);
        this.add(checkBox6);
        checkBoxes.add(checkBox6);
        checkBox7 = new JCheckBox("przygodowa", false);
        checkBox7.setBounds(100,150, 50,50);
        this.add(checkBox7);
        checkBoxes.add(checkBox7);
        checkBox8 = new JCheckBox("komedia", false);
        checkBox8.setBounds(100,150, 50,50);
        this.add(checkBox8);
        checkBoxes.add(checkBox8);
        checkBox9 = new JCheckBox("biografia", false);
        checkBox9.setBounds(100,150, 50,50);
        this.add(checkBox9);
        checkBoxes.add(checkBox9);


        bAdd = new JButton("Add Book");
        bAdd.addActionListener(this);
        add(bAdd);

        bMenu = new JButton("MENU");
        bMenu.addActionListener(this);
        add(bMenu);

        this.setVisible(true);

    }

    public void addBook(){
        try {
            ArticleDAO DataBase = new ArticleDAO();
            DataBase.connect();

            setBackground(new Color(100, 130, 100));
            String kategorie = "";
            for (JCheckBox checkBox:checkBoxes) {
                if (checkBox.isSelected())
                    if(kategorie == "")
                        kategorie = checkBox.getText();
                    else
                        kategorie = kategorie + "," + checkBox.getText();
            }
            String insertSQL = " INSERT INTO Books (tytul, nazwiskoAutora, imionaAutora, rok, kategorie, czyWypozyczona, liczbaWypozyczen) "
                    + "VALUES ("
                    + "'"+ tytulField.getText() + "',"
                    + "'"+ nazwiskoAutoraField.getText() + "',"
                    + "'"+ imionaAutoraField.getText() + "',"
                    + "'"+ parseInt(rokField.getText())  + "',"
                    + "'"+kategorie + "',"
                    + "'"+ 0 +"'"+ ",'" + 0 + "');";

            System.out.println(insertSQL);

            PreparedStatement prepsInsertProduct = DataBase.myConn.prepareStatement(insertSQL, DataBase.myStmt.RETURN_GENERATED_KEYS);

            prepsInsertProduct.executeUpdate();

            DataBase.myStmt.close();
            DataBase.myConn.close();
            System.out.println(" Polecenie " + insertSQL + " wykonane.");

        } catch (Exception e) {
            System.out.println(" Nie mogę dodać danych.\n " + e.getMessage());
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bAdd){
            addBook();
        }else if (source == bMenu) {
            Main.switchPanel(new Menu());
        }
    }

}
