import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information extends JPanel implements ActionListener {
    String list;
    JButton bList,bMenu;
    public Information(String list) {
        this.list = list;

        setLayout(new BorderLayout());
//        setBackground(new Color(255,255,234));


        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(new Color(255,255,234));
        JLabel lNumbers = new JLabel("<html>"
                                        +"<br /><br /><br /><center>Ilość wszystkich książek: <br /><font color=#000000 size=30>"
                                        + Main.DataBase.Library.getNumberOfBooks()
                                        +"</font><br /><br />Obecnie wypożyczone: <br /><font color=#000000 size=30>"
                                        + Main.DataBase.Library.getBorrowed()
                                        +"</font><br /><br />Liczba wypożyczeń: <br /><font color=#000000 size=30>"
                                        + Main.DataBase.Library.getNumberOfBorrowed()+"</font></html>",JLabel.CENTER);

        lNumbers.setFont(new Font("Verdana", Font.PLAIN, 27));

        labelPanel.add(lNumbers);

        add(labelPanel);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,30));
        bottomPanel.setBackground(new Color(255,255,234));
        bottomPanel.setPreferredSize(new Dimension(this.getWidth(),200));
        bottomPanel.setMaximumSize(new Dimension(this.getWidth(),400));

        bMenu = new JButton("MENU");
        bMenu.addActionListener(this);
        bMenu.setPreferredSize(new Dimension(180,40));
        bottomPanel.add(bMenu);

        bList = new JButton("Wróć");
        bList.addActionListener(this);
        bList.setPreferredSize(new Dimension(180,40));
        bottomPanel.add(bList);

        add(bottomPanel,BorderLayout.SOUTH);

        this.setVisible(true);



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
