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
        setBackground(new Color(200, 130, 100));

        JLabel lPodaj = new JLabel("Podaj id książki, która ma zostać "+text+".");
        this.add(lPodaj);
        fId = new JTextField(null,10);
        this.add(fId);

        bOk = new JButton("OK");
        bOk.addActionListener(this);
        this.add(bOk);

        bMenu = new JButton("MENU");
        bMenu.addActionListener(this);
        add(bMenu);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bMenu)
            Main.switchPanel(new Menu());
        else if (source == bOk){
            Main.DataBase.Library.getBook(Integer.parseInt(fId.getText()), action);
            setBackground(new Color(100, 130, 100));
        }

    }
}
