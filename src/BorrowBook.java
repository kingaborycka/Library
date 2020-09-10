import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrowBook extends JPanel implements ActionListener {
    JButton bMenu;

    public BorrowBook() {

        setBackground(new Color(100, 130, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bMenu)
            Main.switchPanel(new Menu());
    }
}
