import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;


public class BooksListFrame extends JPanel implements ActionListener{
    JMenuItem mOther1,mOther2,mOther3,mOther4,mOther5_1,mOther5_2,mOther5_3,mOther5_4,bMenu, bList;
    JMenu bOther,mOther5,bSearch;
    String list, button;

    public BooksListFrame( String list,String button) {
        this.list = list;
        this.button = button;

        UIManager.put("Menu.selectionBackground", new Color(204, 204, 153));
        UIManager.put("Menu.font", new Font("Verdana", Font.PLAIN, 14));
        UIManager.put("Menu.background", new Color(255,255,234));
        UIManager.put("MenuItem.selectionBackground", new Color(204, 204, 153));
        UIManager.put("MenuItem.font", new Font("Verdana", Font.PLAIN, 14));
        UIManager.put("MenuItem.background", new Color(255,255,234));


        setLayout(new GridLayout(1,1));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255,255,234));


        bMenu = new JMenuItem("Menu");
        bMenu.addActionListener(this);
        menuBar.add(bMenu);
//
        bList = new JMenuItem(button);
        bList.addActionListener(this);
        menuBar.add(bList);
//
        bSearch = new JMenu("Wyszukaj");
        bSearch.setMnemonic(KeyEvent.VK_W);

        menuBar.add(bSearch);

        bOther = new JMenu("Inne");
        bOther.setMnemonic(KeyEvent.VK_I);

        mOther1 = new JMenuItem("Obecny stan biblioteki");
        mOther1.addActionListener(this);
        bOther.add(mOther1);
        mOther1.setPreferredSize(bOther.getPreferredSize());
        mOther2 = new JMenuItem("Najczęściej wypożyczane egzemplarze");
        mOther2.addActionListener(this);
        bOther.add(mOther2);
        mOther3 = new JMenuItem("Najbardziej poczytne książki");
        mOther3.addActionListener(this);
        bOther.add(mOther3);
        mOther4 = new JMenuItem("Najbardziej poczytni autorzy");
        mOther4.addActionListener(this);
        bOther.add(mOther4);
        mOther5 = new JMenu("Sortuj wg");

        mOther5_1 = new JMenuItem("Nazwisko autora");
        mOther5_2 = new JMenuItem("Rok wydania");
        mOther5_3 = new JMenuItem("Liczba wypożyczeń");
        mOther5_4 = new JMenuItem("Tytuł");

        mOther5.add(mOther5_1);
        mOther5.add(mOther5_2);
        mOther5.add(mOther5_3);
        mOther5.add(mOther5_4);
        bOther.add(mOther5);
        menuBar.add(bOther);
        panel.add(menuBar, BorderLayout.NORTH);

        JTable BooksTable = new JTable(0,7){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        if(list == "short")BooksTable.setModel(new DefaultTableModel(Main.DataBase.Library.getShortListData(),Main.DataBase.Library.getShortListColumns()));
        else BooksTable.setModel(new BooksTableModel(Main.DataBase.Library.listaKsiazek,Main.DataBase.Library.getLongListColumns()));


        BooksTable.setFont(new Font("Verdana", Font.ITALIC,16));
        BooksTable.setRowHeight(34*Main.skala);
        BooksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        BooksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        BooksTable.setBounds(0,0,getWidth(),getHeight());
        BooksTable.setSelectionBackground(new Color(204, 204, 102));

        JScrollPane scrollPane = new JScrollPane(BooksTable);
        scrollPane.setBounds(0,30*Main.skala,getWidth(),getHeight());
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== bList){
            if(list == "long") Main.switchPanel(new BooksListFrame("short","Więcej informacji"));
            else Main.switchPanel(new BooksListFrame("long","Mniej informacji"));
        }else if (e.getSource()==bMenu)
            Main.switchPanel(new Menu(getWidth(),getHeight()));
        else if (e.getSource()==mOther1)
            Main.switchPanel(new Information(list,"label","numbers"));
        else if (e.getSource()==mOther2)
            Main.switchPanel(new Information(list,"table","mostPopularBooks"));
        else if (e.getSource()==mOther3)
            Main.switchPanel(new Information(list,"table","mostPopularBooksOfCategory"));
        else if (e.getSource()==mOther4)
            Main.switchPanel(new Information(list,"label","mostPopularAutors"));

    }
}