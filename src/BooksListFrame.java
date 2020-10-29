import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.Book;
import java.util.EventObject;


public class BooksListFrame extends JPanel implements ActionListener{
    JMenuItem mOther1,mOther2,mOther3,mSearch1,mSearch2,mSearch3,bMenu,bList;
    JMenu bOther,bSearch;
    String list,button;
    TableModel table;

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

        bList = new JMenuItem(button);
        bList.addActionListener(this);
        menuBar.add(bList);

        bSearch = new JMenu("Wyszukaj...");
        bSearch.setMnemonic(KeyEvent.VK_W);

        mSearch1 = new JMenuItem("...nazwisko autora");
        mSearch1.addActionListener(this);
        bSearch.add(mSearch1);
        mSearch1.setPreferredSize(mSearch1.getPreferredSize());
        mSearch2 = new JMenuItem("...kategorię");
        mSearch2.addActionListener(this);
        bSearch.add(mSearch2);
        mSearch3 = new JMenuItem("...tutuł");
        mSearch3.addActionListener(this);
        bSearch.add(mSearch3);
        menuBar.add(bSearch);

        bOther = new JMenu("Inne");
        bOther.setMnemonic(KeyEvent.VK_I);

        mOther1 = new JMenuItem("Obecny stan biblioteki");
        mOther1.addActionListener(this);
        bOther.add(mOther1);
        mOther1.setPreferredSize(bOther.getPreferredSize());
        mOther2 = new JMenuItem("Najczęściej wypożyczane");
        mOther2.addActionListener(this);
        bOther.add(mOther2);
        mOther3 = new JMenuItem("Najbardziej poczytni autorzy");
        mOther3.addActionListener(this);
        bOther.add(mOther3);
        menuBar.add(bOther);
        panel.add(menuBar, BorderLayout.NORTH);

        if(list == "short") table = new BooksTableModel(Main.DataBase.Library.listaKsiazek,Main.DataBase.Library.getShortListColumns(),list);
        else table = new BooksTableModel(Main.DataBase.Library.listaKsiazek,Main.DataBase.Library.getLongListColumns(),list);


        JTable BooksTable = new JTable(table);

        BooksTable.setFont(new Font("Verdana", Font.ITALIC,14));
        BooksTable.setRowHeight(34*Main.skala);
        BooksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        BooksTable.setBounds(0,0,getWidth(),getHeight());
        BooksTable.setSelectionBackground(new Color(204, 204, 102));
        BooksTable.setAutoCreateRowSorter(true);
        BooksTableModel.resizeColumnWidth(BooksTable);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int x = 0; x < BooksTable.getColumnCount();x++){
            BooksTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }

        TableRowSorter<BooksTableModel> sorter = new TableRowSorter(BooksTable.getModel());
        BooksTable.setRowSorter(sorter);
        sorter.setSortable(4,false);
        sorter.setSortable(5,false);


        JScrollPane scrollPane = new JScrollPane(BooksTable);
        scrollPane.setBounds(0,30*Main.skala,getWidth(),getHeight());
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        switch (e.getSource()){
//            case bList:
//                if(list == "long") Main.switchPanel(new BooksListFrame("short","Więcej informacji"));
//                else Main.switchPanel(new BooksListFrame("long","Mniej informacji"));
//                break;
//            case bMenu:
//                Main.switchPanel(new Menu(getWidth(),getHeight()));
//                break;
//            case mOther1:
//                Main.switchPanel(new Information(list,"label","numbers"));
//                break;
//            case mOther2:
//                Main.switchPanel(new Information(list,"table","mostPopularBooks"));
//                break;
//            case mOther3:
//                Main.switchPanel(new Information(list,"label","mostPopularAutors"));
//                break;
//        }

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
            Main.switchPanel(new Information(list,"label","mostPopularAutors"));
        else if (e.getSource()==mSearch1)
            Main.switchPanel(new Searching(list,"nazwisko autora",null,null));
        else if (e.getSource()==mSearch2)
            Main.switchPanel(new Searching(list,"kategorię",null,null));
        else if (e.getSource()==mSearch3)
            Main.switchPanel(new Searching(list,"tytuł",null,null));


    }
}