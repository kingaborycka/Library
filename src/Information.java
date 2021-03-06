import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Information extends JPanel implements ActionListener {
    String list, labelText;
    JButton bList, bMenu;
    JLabel lTop;

    public Information(String list, String center, String whichList) {
        this.list = list;

        setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 255, 234));
        centerPanel.setLayout(new BorderLayout());
        //..........................................................................................................................
        JLabel label = new JLabel("", JLabel.CENTER);

        label.setFont(new Font("Verdana", Font.PLAIN, 27));


        if (whichList == "numbers") {
            label.setText("<html>"
                    + "<br /><br /><br /><center>Ilość wszystkich książek: <br /><font color=#000000 size=30>"
                    + Main.DataBase.Library.getNumberOfBooks()
                    + "</font><br /><br />Obecnie wypożyczone: <br /><font color=#000000 size=30>"
                    + Main.DataBase.Library.getBorrowed()
                    + "</font><br /><br />Liczba wypożyczeń: <br /><font color=#000000 size=30>"
                    + Main.DataBase.Library.getNumberOfBorrowed() + "</font></html>");
        } else {
            label.setText(printAutors(Main.DataBase.Library.mostPopularAutors()));

        }
        //............................................................................................................................
        JTable tMostPopular = new JTable() {
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
        tMostPopular.setFont(new Font("Verdana", Font.ITALIC, 16));
        tMostPopular.setRowHeight(36 * Main.skala);
        tMostPopular.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tMostPopular.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tMostPopular.setSelectionBackground(new Color(204, 204, 102));


        List<MyBook> listaKsiazek;

        if (whichList == "mostPopularBooks")
            labelText = "Najczęściej wypożyczane:";
            listaKsiazek = Main.DataBase.Library.mostPopularBooks();

        tMostPopular.setModel(new BooksTableModel(listaKsiazek,Main.DataBase.Library.getShortListColumns(),list));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int x = 0; x < tMostPopular.getColumnCount();x++){
            tMostPopular.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }


        //.........................................................................................................................

        if (center == "label")
            centerPanel.add(label);
        else if (center == "table") {
            JPanel topPanel = new JPanel();
            lTop = new JLabel("<html>"
                    + "<br /><br /><center>"
                    + labelText
                    + "</center><br /></html>");
            lTop.setFont(new Font("Verdana", Font.PLAIN, 26));
            topPanel.setBackground(new Color(255, 255, 234));
            topPanel.add(lTop);
            add(topPanel, BorderLayout.NORTH);

            JScrollPane scrollPane = new JScrollPane(tMostPopular);
            centerPanel.add(scrollPane, BorderLayout.CENTER);
        }
        add(centerPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        bottomPanel.setBackground(new Color(255, 255, 234));
        bottomPanel.setPreferredSize(new Dimension(this.getWidth(), 200));
        bottomPanel.setMaximumSize(new Dimension(this.getWidth(), 400));

        bMenu = new JButton("MENU");
        bMenu.addActionListener(this);
        bMenu.setPreferredSize(new Dimension(180, 40));
        bottomPanel.add(bMenu);

        bList = new JButton("Wróć");
        bList.addActionListener(this);
        bList.setPreferredSize(new Dimension(180, 40));
        bottomPanel.add(bList);

        add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bList) {
            if (list == "long") Main.switchPanel(new BooksListFrame("long", "Mniej informacji"));
            else Main.switchPanel(new BooksListFrame("short", "Więcej informacji"));
        } else if (e.getSource() == bMenu)
            Main.switchPanel(new Menu(getWidth(), getHeight()));
    }


    public String printAutors(List<Map.Entry<String,MyBook>> entryList) {
        int place = 0;
        Iterator it = entryList.iterator();
        String lText = "<html><br /><center>Najbardziej poczytni autorzy i ich najpopularniejsze książki:<br /> ";

        while (it.hasNext()) {
            place++;
            Map.Entry<String,MyBook> pair = (Map.Entry<String,MyBook>) it.next();
            lText += "<font color=#354036 size=5><br />"+place+". "+pair.getKey()+" </font><br /><font color=#464746 size=4>"+pair.getValue().getTytul()+"</font>";
            it.remove();

        }
        return lText;
    }
}

