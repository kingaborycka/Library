import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel implements ActionListener{
    static class MyButton extends JButton {
        private Font MyFont = new Font("Verdana", Font.ITALIC, 30);
        private Color BackgroundColor = new Color(255,255,234);
        private Color hoverBackgroundColor = new Color(204, 204, 102);
        private Color pressedBackgroundColor = new Color(204, 204, 153);

        public MyButton(String text) {
            super(text);
            super.setContentAreaFilled(false);
            super.setBackground(BackgroundColor);
            super.setFont(MyFont);
            super.setBorder(BorderFactory.createBevelBorder(0));
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
            }else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

    }

    public static ArticleDAO DataBase;
    MyButton bBooksList, bAddBook, bBorrowBook, bReturnBook, bEditBook;
    private static int width;
    private static int height;

    public Menu(int width, int height) {
        this.width = width;
        this.height = height;

        setLayout(new GridLayout(5,1));

        DataBase = new ArticleDAO();
        DataBase.getData();

        bBooksList = new MyButton("LISTA KSIĄŻEK");
        bBooksList.setBounds(0,0,width,height/5);
        bBooksList.addActionListener(this);
        add(bBooksList);

        bAddBook = new MyButton("DODAJ KSIĄŻKĘ");
        bAddBook.setBounds(0,height/5,width,height/5);
        bAddBook.addActionListener(this);
        add(bAddBook);

        bBorrowBook = new MyButton("WYPOŻYCZ KSIĄŻKĘ");
        bBorrowBook.setBounds(0,2*(height/5),width,height/5);
        bBorrowBook.addActionListener(this);
        add(bBorrowBook);

        bReturnBook = new MyButton("ZWRÓĆ KSIĄŻKĘ");
        bReturnBook.setBounds(0,3*(height/5),width,height/5);
        bReturnBook.addActionListener(this);
        add(bReturnBook);

        bEditBook = new MyButton("EDYTUJ KSIĄŻKĘ");
        bEditBook.setBounds(0,4*(height/5),width,height/5);
        bEditBook.addActionListener(this);
        add(bEditBook);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bBooksList) {
            Main.switchPanel(new BooksListFrame("short"));

        }else if (source == bAddBook) {
            Main.switchPanel(new AddEditBook());

        }else if (source == bReturnBook) {
            Main.switchPanel(new BookId("r","zwrócona"));
        }else if (source == bBorrowBook) {
            Main.switchPanel(new BookId("b","wypożyczona"));
        }else if (source == bEditBook) {
            Main.switchPanel(new BookId("e","edytowana"));
        }
    }


}
