import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class AddEditBook extends JPanel implements ActionListener{

    JButton bMenu, bAddEdit;
    JTextField tytulField, nazwiskoAutoraField, imionaAutoraField, rokField;
    JCheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9;
    ArrayList<JCheckBox> checkBoxes;
    String action,tytul,nazwisko,imiona,rok,button;
    String[] kategorie;
    int id;

    public AddEditBook(MyBook book){
        id = book.getId();
        action = "edit";
        tytul = book.getTytul().replace('"',' ').strip();
        nazwisko =book.getNazwiskoAutora();
        imiona = book.getImionaAutora();
        rok = String.valueOf(book.getRok());
        kategorie = book.getKategorie().split(",");
        button = "Edytuj";

        AddEditLayout();
    }
    public AddEditBook() {
        action = "add";
        tytul = "Tytuł";
        nazwisko = "Nazwisko autora";
        imiona = "Imiona autora";
        rok ="Rok";
        button = "Dodaj książkę";
        kategorie = null;

        AddEditLayout();

    }
    private Boolean check(String kategoria){
        if (kategorie!= null) {
            for (String k : kategorie) {
                k = k.strip();
                if (kategoria.equals(k)) {
                    System.out.println("yes");
                    return true;
                }
            }
        }
        return false;
    }

    public void AddEditLayout(){
        this.setLayout(new GridLayout(1,2,50,20));
        this.setBackground(new Color(255,255,234));

        JPanel left = new JPanel();
        left.setLayout(null);
        left.setBackground(new Color(255,255,234));

        this.add(left);

        JPanel right = new JPanel();
        right.setLayout(null);
        right.setBackground(new Color(255,255,234));
        this.add(right);

        tytulField = new JTextField(tytul,10);
        tytulField.setBounds(75,100,300,50);
        tytulField.setFont(new Font("Verdana", Font.ITALIC, 16));
        tytulField.setHorizontalAlignment(JTextField.CENTER);
        tytulField.setBorder(new LineBorder(new Color(204, 204, 102), 3));
        left.add(tytulField);

        nazwiskoAutoraField = new JTextField(nazwisko,10);
        nazwiskoAutoraField.setBounds(75,170,300,50);
        nazwiskoAutoraField.setFont(new Font("Verdana", Font.ITALIC, 16));
        nazwiskoAutoraField.setHorizontalAlignment(JTextField.CENTER);
        nazwiskoAutoraField.setBorder(new LineBorder(new Color(204, 204, 102), 3));
        left.add(nazwiskoAutoraField);

        imionaAutoraField = new JTextField(imiona,10);
        imionaAutoraField.setBounds(75,240,300,50);
        imionaAutoraField.setFont(new Font("Verdana", Font.ITALIC, 16));
        imionaAutoraField.setHorizontalAlignment(JTextField.CENTER);
        imionaAutoraField.setBorder(new LineBorder(new Color(204, 204, 102), 3));
        left.add(imionaAutoraField);

        rokField = new JTextField(rok,10);
        rokField.setBounds(75,310,300,50);
        rokField.setFont(new Font("Verdana", Font.ITALIC, 16));
        rokField.setHorizontalAlignment(JTextField.CENTER);
        rokField.setBorder(new LineBorder(new Color(204, 204, 102), 3));
        left.add(rokField);

        bAddEdit = new JButton(button);
        bAddEdit.addActionListener(this);
        bAddEdit.setBounds(150,400,160,30);
        left.add(bAddEdit);

        bMenu = new JButton("Menu");
        bMenu.addActionListener(this);
        bMenu.setBounds(150,450,160,30);

        left.add(bMenu);


        checkBoxes = new ArrayList<>();

        checkBox1 = new JCheckBox("literatura piękna",false);
        checkBox1.setSelected(check(checkBox1.getText()));
        checkBox1.setBounds(50,100, 300,30);
        checkBox1.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox1.setBackground(new Color(255,255,234));
        right.add(checkBox1);
        checkBoxes.add(checkBox1);
        checkBox2 = new JCheckBox("romans",false);
        checkBox2.setSelected(this.check(checkBox2.getText()));
        checkBox2.setBounds(50,140, 300,30);
        checkBox2.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox2.setBackground(new Color(255,255,234));
        right.add(checkBox2);
        checkBoxes.add(checkBox2);
        checkBox3 = new JCheckBox("powieść historyczna",false);
        checkBox3.setSelected(this.check(checkBox3.getText()));
        checkBox3.setBounds(50,180, 300,30);
        checkBox3.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox3.setBackground(new Color(255,255,234));
        right.add(checkBox3);
        checkBoxes.add(checkBox3);
        checkBox4 = new JCheckBox("fantasy",false);
        checkBox4.setSelected(this.check(checkBox4.getText()));
        checkBox4.setBounds(50,220, 300,30);
        checkBox4.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox4.setBackground(new Color(255,255,234));
        right.add(checkBox4);
        checkBoxes.add(checkBox4);
        checkBox5 = new JCheckBox("obyczajowa",false);
        checkBox5.setSelected(check(checkBox5.getText()));
        checkBox5.setBounds(50,260, 300,30);
        checkBox5.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox5.setBackground(new Color(255,255,234));
        right.add(checkBox5);
        checkBoxes.add(checkBox5);
        checkBox6 = new JCheckBox("kryminał",false);
        checkBox6.setSelected(check(checkBox6.getText()));
        checkBox6.setBounds(50,300, 300,30);
        checkBox6.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox6.setBackground(new Color(255,255,234));
        right.add(checkBox6);
        checkBoxes.add(checkBox6);
        checkBox7 = new JCheckBox("przygodowa",false);
        checkBox7.setSelected(this.check(checkBox7.getText()));
        checkBox7.setBounds(50,340, 300,30);
        checkBox7.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox7.setBackground(new Color(255,255,234));
        right.add(checkBox7);
        checkBoxes.add(checkBox7);
        checkBox8 = new JCheckBox("komedia",false);
        checkBox8.setSelected(check(checkBox8.getText()));
        checkBox8.setBounds(50,380, 300,30);
        checkBox8.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox8.setBackground(new Color(255,255,234));
        right.add(checkBox8);
        checkBoxes.add(checkBox8);
        checkBox9 = new JCheckBox("biografia",false);
        checkBox9.setSelected(check(checkBox9.getText()));
        checkBox9.setBounds(50,420, 300,30);
        checkBox9.setFont(new Font("Verdana", Font.ITALIC, 20));
        checkBox9.setBackground(new Color(255,255,234));
        right.add(checkBox9);
        checkBoxes.add(checkBox9);


        this.setVisible(true);
    }

    public void addEditBook(String action){
        String insertSQL;
        try {
            ArticleDAO DataBase = new ArticleDAO();
            DataBase.connect();
            String kategorie = "",message;

            for (JCheckBox checkBox:checkBoxes) {
                if (checkBox.isSelected())
                    if(kategorie.equals(""))
                        kategorie = checkBox.getText();
                    else
                        kategorie = kategorie + "," + checkBox.getText();
            }
            if (action.equals("add")){
                message = "dodana";
                insertSQL = " INSERT INTO Books (tytul, nazwiskoAutora, imionaAutora, rok, kategorie, czyWypozyczona, liczbaWypozyczen) "
                        + "VALUES ("
                        + "'"+ tytulField.getText() + "',"
                        + "'"+ nazwiskoAutoraField.getText() + "',"
                        + "'"+ imionaAutoraField.getText() + "',"
                        + "'"+ parseInt(rokField.getText())  + "',"
                        + "'"+kategorie + "',"
                        + "'"+ 0 +"'"+ ",'" + 0 + "');";
            }else{
                message = "edytowana";
                insertSQL = " UPDATE Books SET "
                        + "tytul = '"+ tytulField.getText() + "',"
                        + "nazwiskoAutora = '"+ nazwiskoAutoraField.getText() + "',"
                        + "imionaAutora = '"+ imionaAutoraField.getText() + "',"
                        + "rok = '"+ parseInt(rokField.getText())  + "',"
                        + "kategorie = '"+kategorie + "'"
                        + "WHERE id = "+id +";";

            }

            PreparedStatement prepsInsertProduct = DataBase.myConn.prepareStatement(insertSQL, DataBase.myStmt.RETURN_GENERATED_KEYS);

            prepsInsertProduct.executeUpdate();

            DataBase.myStmt.close();
            DataBase.myConn.close();
            System.out.println(" Polecenie " + insertSQL + " wykonane.");
            JOptionPane.showMessageDialog(null, "Książka została "+message+".");
            Main.switchPanel(new Menu(getWidth(),getHeight()));

        } catch (Exception e) {
            System.out.println(" Nie mogę dodać danych.\n " + e.getMessage());
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bAddEdit){
                addEditBook(action);

        }else if (source == bMenu) {
            Main.switchPanel(new Menu(getWidth(),getHeight()));
        }
    }

}
