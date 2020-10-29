import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.List;

public class Searching extends JPanel implements ActionListener {
    private String kryterium,list;
    private JLabel lTop;
    private String kategoria;
    private BooksTableModel table;
    JButton bMenu,bList,bSearch;
    private JTextField fPattern;
    private List<MyBook> listaKsiazek;

    public Searching(String list,String kategoria,String pattern,List<MyBook> listaKsiazek){
        this.list = list;
        this.listaKsiazek = listaKsiazek;
        this.kategoria = kategoria;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2,1));
        lTop = new JLabel("<html>"
                + "<br /><center>Podaj "
                + kategoria //{nazwisko autora, kategorię, tytuł}
                + " książki:</center></html>");
        lTop.setHorizontalAlignment(JLabel.CENTER);
        lTop.setFont(new Font("Verdana", Font.PLAIN, 26));
        topPanel.setBackground(new Color(255, 255, 234));
        topPanel.setPreferredSize(new Dimension(this.getWidth(), 150));
        topPanel.add(lTop);


        JPanel fieldPanel = new JPanel(new FlowLayout());
        fieldPanel.setBackground(new Color(255,255,234));
        fPattern = new JTextField(pattern,20);
        fPattern.setFont(new Font("Verdana", Font.ITALIC, 18));
        fPattern.setPreferredSize(new Dimension(300,40));
        fPattern.setHorizontalAlignment(JTextField.CENTER);
        fPattern.setBorder(new LineBorder(new Color(204, 204, 102), 3));
        fieldPanel.add(fPattern);

        bSearch = new JButton("Szukaj");
        bSearch.addActionListener(this);
        bSearch.setPreferredSize(new Dimension(100,40));

        fieldPanel.add(bSearch);
        topPanel.add(fieldPanel);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 255, 234));
        centerPanel.setLayout(new BorderLayout());

        JTable table = new JTable() {
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
        table.setFont(new Font("Verdana", Font.ITALIC, 16));
        table.setRowHeight(36 * Main.skala);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setSelectionBackground(new Color(204, 204, 102));

        if (this.listaKsiazek!=null)
            table.setModel(new BooksTableModel(this.listaKsiazek,Main.DataBase.Library.getLongListColumns(),this.list));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int x = 0; x < table.getColumnCount();x++){
            table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }



        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        bottomPanel.setBackground(new Color(255, 255, 234));
        bottomPanel.setPreferredSize(new Dimension(this.getWidth(), 100));
        bottomPanel.setMaximumSize(new Dimension(this.getWidth(), 300));

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
            if (this.list == "long") Main.switchPanel(new BooksListFrame("long", "Mniej informacji"));
            else Main.switchPanel(new BooksListFrame("short", "Więcej informacji"));
        } else if (e.getSource() == bMenu) {
            Main.switchPanel(new Menu(getWidth(), getHeight()));
        }else if (e.getSource() == bSearch) {
            listaKsiazek = Main.DataBase.Library.searcher(fPattern.getText(), kategoria);
            Main.switchPanel(new Searching(this.list, this.kategoria, fPattern.getText(), listaKsiazek));
        }
    }
}
