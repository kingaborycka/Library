import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrowBook extends JPanel implements ActionListener {
    JButton bMenu,bOk;
    JTextField fId;

    public BorrowBook() {

        setBackground(new Color(200, 130, 100));
        JLabel lPodaj = new JLabel("Podaj id książki, która ma zostać wypożyczyczona.");
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
        if (source == bMenu) {
            Main.switchPanel(new Menu());
        }else if (source == bOk){
            Main.DataBase.Library.borrowBook(Integer.parseInt(fId.getText()));
            setBackground(new Color(100, 130, 100));

        }

    }
}
