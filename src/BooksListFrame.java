import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;


public class BooksListFrame extends JPanel implements ActionListener {
    Menu.MyButton bMenu,bLongList;
    JTextField fSearch;

    public BooksListFrame(String list) {
        setLayout(new GridLayout(1,1));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,2,0,10));
        topPanel.setPreferredSize(new Dimension(Main.windowWidth,Main.windowHeight/15));

        JPanel leftPanel = new JPanel();
        topPanel.add(leftPanel);
        leftPanel.setLayout(new BorderLayout(10,10));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2,0,0));

        bMenu = new Menu.MyButton("MENU");
        bMenu.addActionListener(this);
        bMenu.setFont(new Font("Verdana", Font.PLAIN, 15));
        buttonsPanel.add(bMenu);

        bLongList = new Menu.MyButton("WIĘCEJ INFORMACJI");
        bLongList.setFont(new Font("Verdana", Font.PLAIN, 15));
        bLongList.setBounds(0,10,leftPanel.getWidth()/2,leftPanel.getHeight()/2);
        bLongList.addActionListener(this);
        buttonsPanel.add(bLongList);

        fSearch = new JTextField("WYSZUKAJ");
        fSearch.setHorizontalAlignment(JTextField.RIGHT);


        leftPanel.add(buttonsPanel);
        topPanel.add(fSearch);
        panel.add(topPanel, BorderLayout.NORTH);

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

        if(list == "short")BooksTable.setModel(new DefaultTableModel(Main.DataBase.Library.getShortListData(),Main.DataBase.Library.getShortListColumns()));
        else BooksTable.setModel(new DefaultTableModel(Main.DataBase.Library.getLongListData(),Main.DataBase.Library.getLongListColumns()));


        BooksTable.setFont(new Font("Verdana", Font.ITALIC,16));
        BooksTable.setRowHeight(34*Main.skala);
        BooksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        BooksTable.setBounds(0,0,getWidth(),getHeight());
        BooksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        BooksTable.setSelectionBackground(new Color(204, 204, 102));

        JScrollPane scrollPane = new JScrollPane(BooksTable);
        scrollPane.setBounds(0,30*Main.skala,getWidth(),getHeight());
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bMenu) {
            Main.switchPanel(new Menu(getWidth(),getHeight()));
        }else if (source == bLongList) {
            Main.switchPanel(new BooksListFrame("long"));
        }
    }
}