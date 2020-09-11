import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BooksListFrame extends JPanel implements ActionListener {
    JButton bMenu;

    public BooksListFrame() {
        setLayout(new BorderLayout());
        setBackground(new Color(40, 140, 190));

        JTable BooksTable = new JTable(0,7);
        BooksTable.setModel(new DefaultTableModel(Main.DataBase.Library.getShortListData(), Main.DataBase.Library.getShortListColumns()));
        BooksTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        BooksTable.setFont(new Font("Serif", Font.CENTER_BASELINE,16));
        BooksTable.setRowHeight(24);
        BooksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(BooksTable);
        add(scrollPane,BorderLayout.CENTER);

        bMenu = new JButton("MENU");
        bMenu.addActionListener(this);
        add(bMenu, BorderLayout.PAGE_END);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bMenu) {
            Main.switchPanel(new Menu());
        }
    }
}