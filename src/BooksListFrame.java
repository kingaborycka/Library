import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BooksListFrame extends JPanel implements ActionListener {
    Menu.MyButton bMenu;

    public BooksListFrame() {
        setLayout(new GridLayout(1,1));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255,255,234));

        JTable BooksTable = new JTable(0,7){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        BooksTable.setModel(new DefaultTableModel(Main.DataBase.Library.getShortListData(), Main.DataBase.Library.getShortListColumns()));
        BooksTable.setFont(new Font("Verdana", Font.ITALIC,16));
        BooksTable.setRowHeight(34*Main.skala);
        BooksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        BooksTable.setBounds(0,0,getWidth(),getHeight());
        BooksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        BooksTable.setSelectionBackground(new Color(204, 204, 102));

        JScrollPane scrollPane = new JScrollPane(BooksTable);
        scrollPane.setBounds(0,30*Main.skala,getWidth(),getHeight());
        scrollPane.setBackground(new Color(255,255,234));
        panel.add(scrollPane, BorderLayout.CENTER);



        bMenu = new Menu.MyButton("POWRÓT DO MENU");
        bMenu.addActionListener(this);
        bMenu.setBounds(0,0,getWidth(),30*Main.skala);
        bMenu.setFont(new Font("Verdana", Font.ITALIC, 15));
        panel.add(bMenu, BorderLayout.NORTH);
        add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bMenu) {
            Main.switchPanel(new Menu(getWidth(),getHeight()));
        }
    }
}