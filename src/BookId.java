import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookId extends JPanel implements ActionListener {
    JButton bMenu, bOk;
    JTextField fId;
    String text, action;

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
        bookInfPanel.setBackground(new Color(255,255,234));

        add(bookInfPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255,255,234));

        bOk = new JButton("OK");
        bOk.addActionListener(this);
        bOk.setPreferredSize(new Dimension(180,40));
        bottomPanel.add(bOk);

        bMenu = new JButton("MENU");
        bMenu.addActionListener(this);
        bMenu.setPreferredSize(new Dimension(180,40));
        bottomPanel.add(bMenu);
        add(bottomPanel);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bMenu)
            Main.switchPanel(new Menu(getWidth(),getHeight()));
        else if (source == bOk){
            Main.DataBase.Library.getBook(Integer.parseInt(fId.getText()), action);
            setBackground(new Color(100, 130, 100));
        }

    }
}
