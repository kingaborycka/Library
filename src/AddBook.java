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
        setLayout(new GridLayout(1,2,50,20));

        JPanel left = new JPanel();
        left.setLayout(null);
        add(left);

        JPanel right = new JPanel();
        right.setLayout(null);
        add(right);


        tytulField = new JTextField("Tytuł", 10);
        tytulField.setBounds(75,100,300,50);
        left.add(tytulField);

        nazwiskoAutoraField = new JTextField("Nazwisko", 10);
        nazwiskoAutoraField.setBounds(75,170,300,50);
        left.add(nazwiskoAutoraField);

        imionaAutoraField = new JTextField("Imiona", 10);
        imionaAutoraField.setBounds(75,240,300,50);
        left.add(imionaAutoraField);

        rokField = new JTextField("2000", 10);
        rokField.setBounds(75,310,300,50);
        left.add(rokField);

        bAdd = new JButton("Dodaj książkę");
        bAdd.addActionListener(this);
        bAdd.setBounds(150,400,160,30);
        left.add(bAdd);

        bMenu = new JButton("Menu");
        bMenu.addActionListener(this);
        bMenu.setBounds(150,450,160,30);

        left.add(bMenu);


        checkBoxes = new ArrayList<>();

        checkBox1 = new JCheckBox("literatura piękna", false);
        checkBox1.setBounds(50,100, 300,30);
        checkBox1.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox1);
        checkBoxes.add(checkBox1);
        checkBox2 = new JCheckBox("romans", false);
        checkBox2.setBounds(50,140, 300,30);
        checkBox2.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox2);
        checkBoxes.add(checkBox2);
        checkBox3 = new JCheckBox("powieść historyczna", false);
        checkBox3.setBounds(50,180, 300,30);
        checkBox3.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox3);
        checkBoxes.add(checkBox3);
        checkBox4 = new JCheckBox("fantasy", false);
        checkBox4.setBounds(50,220, 300,30);
        checkBox4.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox4);
        checkBoxes.add(checkBox4);
        checkBox5 = new JCheckBox("obyczajowa", false);
        checkBox5.setBounds(50,260, 300,30);
        checkBox5.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox5);
        checkBoxes.add(checkBox5);
        checkBox6 = new JCheckBox("kryminał", false);
        checkBox6.setBounds(50,300, 300,30);
        checkBox6.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox6);
        checkBoxes.add(checkBox6);
        checkBox7 = new JCheckBox("przygodowa", false);
        checkBox7.setBounds(50,340, 300,30);
        checkBox7.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox7);
        checkBoxes.add(checkBox7);
        checkBox8 = new JCheckBox("komedia", false);
        checkBox8.setBounds(50,380, 300,30);
        checkBox8.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox8);
        checkBoxes.add(checkBox8);
        checkBox9 = new JCheckBox("biografia", false);
        checkBox9.setBounds(50,420, 300,30);
        checkBox9.setFont(new Font("Verdana", Font.ITALIC, 20));
        right.add(checkBox9);
        checkBoxes.add(checkBox9);


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
            Main.switchPanel(new Menu(getWidth(),getHeight()));
        }
    }

}
