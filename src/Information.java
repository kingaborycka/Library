import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information extends JPanel implements ActionListener {
    String list;
    JButton bList,bMenu;
    public Information(String list) {
        this.list = list;

        setLayout(new GridLayout(6,1));
        setBackground(new Color(255,255,234));

        bList = new JButton("Wróć");
        bList.addActionListener(this);
        add(bList);

        bMenu = new JButton("Menu");
        bMenu.addActionListener(this);
        add(bMenu);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==bList){
            if(list == "long") Main.switchPanel(new BooksListFrame("long","Mniej informacji"));
            else Main.switchPanel(new BooksListFrame("short","Więcej informacji"));
        }else if (e.getSource()==bMenu)
            Main.switchPanel(new Menu(getWidth(),getHeight()));
    }
}
