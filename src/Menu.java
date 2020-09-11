import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener{
    JButton bBooksList, bAddBook, bBorrowBook, bReturnBook;


    public Menu() {
        setBackground(new Color(150,150,150));
        setLayout(new GridLayout(4,1));

        bBooksList = new JButton("Lista książek");
        add(bBooksList);
        bBooksList.addActionListener(this);

        bAddBook = new JButton("Dodaj książkę");
        add(bAddBook);
        bAddBook.addActionListener(this);

        bBorrowBook = new JButton("Wypożycz książkę");
        add(bBorrowBook);
        bBorrowBook.addActionListener(this);

        bReturnBook = new JButton("Zwróć książkę");
        add(bReturnBook);
        bReturnBook.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bBooksList) {
            Main.switchPanel(new BooksListFrame());

        }else if (source == bAddBook) {
            Main.switchPanel(new AddBook());

        }else if (source == bReturnBook) {
            Main.switchPanel(new ReturnBook());
        }else if (source == bBorrowBook) {
            Main.switchPanel(new BorrowBook());
        }
    }
}
