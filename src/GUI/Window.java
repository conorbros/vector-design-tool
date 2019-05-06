package GUI;

import Commands.CommandEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener, Runnable {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final int MIN_WIDTH = 1000;
    private static final int MIN_HEIGHT = 1000;

    private VECPanel vecPanel = new VECPanel();

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Window("Vector Design Tool"));
    }

    protected Window(String title) throws HeadlessException{
        super(title);
        createGUI();
    }

    private static class WindowHolder {
        private final static Window INSTANCE = new Window("Vector Design Tool");
    }

    public static Window getInstance(){
        return WindowHolder.INSTANCE;
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
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(vecPanel, BorderLayout.CENTER);

        setJMenuBar(menuBar());

        repaint();
        setVisible(true);
    }


    private JMenuBar menuBar(){
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(fileMenu());
        menuBar.add(commmandMenu());
        menuBar.add(colorMenu());
        //more menu items to go here

        return menuBar;
    }

    private JMenu fileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem exitFile = new JMenuItem("Exit");

        fileMenu.setMnemonic('F');
        openFile.setMnemonic('O');
        saveFile.setMnemonic('S');
        exitFile.setMnemonic('X');

        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(exitFile);

        return fileMenu;
    }

    private JMenu commmandMenu(){
        JMenu commandMenu = new JMenu("Commands");

        JMenuItem line = new JMenuItem("LINE");
        JMenuItem plot = new JMenuItem("PLOT");
        JMenuItem rectangle = new JMenuItem("RECTANGLE");
        JMenuItem ellipse = new JMenuItem("ELLIPSE");
        JMenuItem polygon = new JMenuItem("POLYGON");

        line.setMnemonic('L');
        plot.setMnemonic('P');
        rectangle.setMnemonic('R');
        ellipse.setMnemonic('E');
        polygon.setMnemonic('G');

        commandMenu.add(line);
        commandMenu.add(plot);
        commandMenu.add(rectangle);
        commandMenu.add(ellipse);
        commandMenu.add(polygon);

        line.addActionListener(e -> vecPanel.setSelectedCommand(CommandEnum.LINE));
        plot.addActionListener(e -> vecPanel.setSelectedCommand(CommandEnum.PLOT));
        rectangle.addActionListener(e -> vecPanel.setSelectedCommand(CommandEnum.RECTANGLE));
        ellipse.addActionListener(e -> vecPanel.setSelectedCommand(CommandEnum.ELLIPSE));
        polygon.addActionListener(e -> vecPanel.setSelectedCommand(CommandEnum.POLYGON));

        return commandMenu;
    }

    private JMenu colorMenu(){
        JMenu colorMenu = new JMenu("Colors");

        JMenuItem pen = new JMenuItem("Pen Color");
        JMenuItem fill = new JMenuItem("Fill Color");

        pen.setMnemonic('P');
        fill.setMnemonic('F');

        colorMenu.add(pen);
        colorMenu.add(fill);

        pen.addActionListener(e -> vecPanel.setPenColor(JColorChooser.showDialog(null, "Choose a color", null)));
        fill.addActionListener(e -> vecPanel.setFillColor(JColorChooser.showDialog(null, "Choose a color", null)));

        return colorMenu;
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
