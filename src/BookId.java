import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookId extends JPanel implements ActionListener {
    JButton bMenu, bOk;
    JTextField fId;
    String text, action;
    JLabel BookInf1,BookInf2;
    Boolean next = false;

    public BookId(String action,String text){
        this.text = text;
        this.action = action;

        setLayout(new GridLayout(6,1));
        setBackground(new Color(255,255,234));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255,255,234));
        add(topPanel);

        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(new Color(255,255,234));
        JLabel lPodaj = new JLabel("Podaj id książki, która ma zostać "+text+".");
        lPodaj.setFont(new Font("Verdana", Font.PLAIN, 30));
        labelPanel.add(lPodaj);
        add(labelPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setBackground(new Color(255,255,234));
        fId = new JTextField(null,10);
        fId.setFont(new Font("Verdana", Font.ITALIC, 43));
        fieldPanel.add(fId);
        add(fieldPanel);

        JPanel bookInfPanel = new JPanel();
        bookInfPanel.setLayout(new GridLayout(2,1,0,0));
        bookInfPanel.setBackground(new Color(255,255,234));

        BookInf1 = new JLabel("",JLabel.CENTER);
        BookInf1.setFont(new Font("Verdana", Font.ITALIC, 20));
        bookInfPanel.add(BookInf1);
        BookInf2 = new JLabel("",JLabel.CENTER);
        BookInf2.setFont(new Font("Verdana", Font.ITALIC, 18));
        bookInfPanel.add(BookInf2);
        add(bookInfPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,30));
        bottomPanel.setBackground(new Color(255,255,234));

        bMenu = new JButton("MENU");
        bMenu.addActionListener(this);
        bMenu.setPreferredSize(new Dimension(180,40));
        bottomPanel.add(bMenu);

        bOk = new JButton("OK");
        bOk.addActionListener(this);
        bOk.setPreferredSize(new Dimension(180,40));
        bottomPanel.add(bOk);

        add(bottomPanel);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MyBook book;
        Object source = e.getSource();
        if (source == bMenu)
            Main.switchPanel(new Menu(getWidth(),getHeight()));
        else if (source == bOk){
            book = Main.DataBase.Library.getBook(Integer.parseInt(fId.getText()), action);
            if(next == true) {
              book.getMe(action);
            }else{
                if (book != null)
                    showBookInf(book);
            }
        }

    }

    public void showBookInf(MyBook book){
      BookInf1.setText(
              book.getImionaAutora()
              +" "+book.getNazwiskoAutora()
              +" "+book.getTytul()
      );

      BookInf2.setText(
              book.getRok()
              +" "+book.getKategorie()
      );
      next = true;
    };
}
