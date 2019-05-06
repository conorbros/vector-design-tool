package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooser extends JFrame implements ActionListener {
    private Color color;
    private JButton chooseButton = new JButton("Choose color");

    public ColorChooser(String title) throws HeadlessException {
        super(title);
        setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pnl = new JPanel();

        chooseButton.addActionListener(this);
        pnl.add(chooseButton);
        this.add(pnl);
        this.setVisible(true);
    }

    public static void main(String[] args){
        new ColorChooser("Color Chooser");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Color c = JColorChooser.showDialog(null, "Choose a color", null);
        if (c != null){
            System.out.println(c.toString());
        }
    }
}
