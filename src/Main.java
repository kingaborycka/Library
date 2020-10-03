import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame window;
    public static ArticleDAO DataBase;
    public static int skala;
    public static Dimension d;
    public static int windowWidth;
    public static int windowHeight;

    public static void main(String[] args) {
        d = Toolkit.getDefaultToolkit().getScreenSize();
        skala = d.width/1024;

        window = new JFrame();
        window.setSize(skala*800, skala*630);
        window.setTitle("MyLibrary");
        window.setLocation(550, 200);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowWidth = window.getWidth();
        windowHeight = window.getHeight();
        switchPanel(new Menu(windowWidth,windowHeight));

    }

    public static void switchPanel(JPanel panel) {
        window.getContentPane().removeAll();
        window.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.validate();
        DataBase = new ArticleDAO();
        DataBase.getData();
    }
}
