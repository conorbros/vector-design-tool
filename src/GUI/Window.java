package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener, Runnable {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    private JPanel pnlDisplay;
    private JPanel pnlTop;
    private JPanel pnlLeft;
    private JPanel pnlRight;
    private JPanel pnlBottom;

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Window("Vector Design Tool"));
    }

    public Window(String title) throws HeadlessException{
        super(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        createGUI();
    }

    private void createGUI(){
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pnlDisplay = createPanel(Color.WHITE);
        pnlTop = createPanel(Color.LIGHT_GRAY);
        pnlBottom = createPanel(Color.LIGHT_GRAY);
        pnlLeft = createPanel(Color.LIGHT_GRAY);
        pnlRight = createPanel(Color.LIGHT_GRAY);

        getContentPane().add(pnlDisplay, BorderLayout.CENTER);
        getContentPane().add(pnlTop, BorderLayout.NORTH);
        getContentPane().add(pnlBottom, BorderLayout.SOUTH);
        getContentPane().add(pnlLeft, BorderLayout.EAST);
        getContentPane().add(pnlRight, BorderLayout.WEST);

        repaint();
        setVisible(true);
    }

    private JPanel createPanel(Color c){
        JPanel pnl = new JPanel();
        pnl.setBackground(c);
        return pnl;
    }

    private JButton createButton(String text){
        JButton btn = new JButton();
        btn.setText(text);
        btn.addActionListener(this);
        return btn;
    }
}
