import javax.swing.*;

public class Main {
    private static JFrame window;
    public static ArticleDAO DataBase;

    public static void main(String[] args) {
        window = new JFrame();
        window.setSize(800, 630);
        window.setTitle("MyLibrary");
        window.setLocation(550, 300);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        switchPanel(new Menu());

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
