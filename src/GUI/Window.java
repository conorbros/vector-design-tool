package GUI;

import Commands.CommandType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;


public class Window extends JFrame implements ActionListener, Runnable {
    private static final int WIDTH = 1030;
    private static final int HEIGHT = 1000;
    private static final int MIN_WIDTH = 1030;
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
        add(toolPanel(), BorderLayout.WEST);
        setJMenuBar(menuBar());

        repaint();
        setVisible(true);
    }


    private JMenuBar menuBar(){
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(fileMenu());
        menuBar.add(commmandMenu());
        menuBar.add(colorMenu());
        menuBar.add(historyMenu());
        //more menu items to go here

        return menuBar;
    }

    private JMenu fileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem saveNewFile = new JMenuItem("Save As New");
        JMenuItem exitFile = new JMenuItem("Exit");

        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        fileMenu.setMnemonic('F');
        openFile.setMnemonic('O');
        saveFile.setMnemonic('S');
        saveNewFile.setMnemonic('N');
        exitFile.setMnemonic('X');

        openFile.addActionListener(e -> vecPanel.openFile());

        saveFile.addActionListener(e -> {
            try {
                vecPanel.saveFile();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        saveNewFile.addActionListener(e -> {
            try {
                vecPanel.saveNewFile();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveNewFile);
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

        line.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        plot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        rectangle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        ellipse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        polygon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));

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

        line.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.LINE));
        plot.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.PLOT));
        rectangle.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.RECTANGLE));
        ellipse.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.ELLIPSE));
        polygon.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.POLYGON));

        return commandMenu;
    }

    private JMenu colorMenu(){
        JMenu colorMenu = new JMenu("Colors");

        JMenuItem pen = new JMenuItem("Pen Color");
        JMenuItem fill = new JMenuItem("Fill Color");

        pen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        fill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

        pen.setMnemonic('C');
        fill.setMnemonic('F');

        colorMenu.add(pen);
        colorMenu.add(fill);

        pen.addActionListener(e -> vecPanel.setPenColor(JColorChooser.showDialog(null, "Choose a color", null)));
        fill.addActionListener(e -> vecPanel.setFillColor(JColorChooser.showDialog(null, "Choose a color", null)));

        return colorMenu;
    }

    private JMenu historyMenu(){
        JMenu historyMenu = new JMenu("History");

        JMenuItem undo = new JMenuItem("undo");
        JMenuItem redo = new JMenuItem("redo");

        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

        historyMenu.add(undo);
        historyMenu.add(redo);

        undo.addActionListener(e -> vecPanel.undoCommand());
        redo.addActionListener(e -> vecPanel.redoCommand());

        return historyMenu;
    }

    private JPanel toolPanel(){
        JPanel toolPanel = createPanel(Color.LIGHT_GRAY);
        toolPanel.setSize(new Dimension(30, 1000));
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));

        ButtonGroup penColors = new ButtonGroup();
        JLabel penLabel = new JLabel("Pen color:");
        JRadioButton penBlack = createRadioButton("", Color.BLACK, e -> vecPanel.setPenColor(Color.BLACK));
        penBlack.setSelected(true);
        JRadioButton penRed = createRadioButton("", Color.RED, e -> vecPanel.setPenColor(Color.RED));
        JRadioButton penBlue = createRadioButton("", Color.BLUE, e -> vecPanel.setPenColor(Color.BLUE));
        penColors.add(penBlack);
        penColors.add(penRed);
        penColors.add(penBlue);

        toolPanel.add(penLabel);
        toolPanel.add(penBlack);
        toolPanel.add(penRed);
        toolPanel.add(penBlue);

        ButtonGroup fillColors = new ButtonGroup();
        JLabel fillLabel = new JLabel("Fill color:");
        JRadioButton fillNull = createRadioButton("no fill", Color.LIGHT_GRAY, e -> vecPanel.setFillColor(null));
        fillNull.setSelected(true);
        JRadioButton fillWhite = createRadioButton("", Color.WHITE, e -> vecPanel.setFillColor(Color.WHITE));
        JRadioButton fillRed = createRadioButton("", Color.RED, e -> vecPanel.setFillColor(Color.RED));
        JRadioButton fillBlue = createRadioButton("", Color.BLUE, e -> vecPanel.setFillColor(Color.BLUE));
        fillColors.add(fillNull);
        fillColors.add(fillWhite);
        fillColors.add(fillRed);
        fillColors.add(fillBlue);

        toolPanel.add(fillLabel);
        toolPanel.add(fillNull);
        toolPanel.add(fillWhite);
        toolPanel.add(fillRed);
        toolPanel.add(fillBlue);

        ButtonGroup commands = new ButtonGroup();
        JLabel commandsLabel = new JLabel("Commands:");
        JRadioButton plot = createRadioButton("PLOT", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandType.PLOT));
        plot.setSelected(true);
        JRadioButton line = createRadioButton("LINE", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandType.LINE));
        JRadioButton rectangle = createRadioButton("RECTANGLE", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandType.RECTANGLE));
        JRadioButton ellipse = createRadioButton("ELLIPSE", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandType.ELLIPSE));
        JRadioButton polygon = createRadioButton("POLYGON", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandType.POLYGON));
        commands.add(plot);
        commands.add(line);
        commands.add(rectangle);
        commands.add(ellipse);
        commands.add(polygon);

        toolPanel.add(commandsLabel);
        toolPanel.add(plot);
        toolPanel.add(line);
        toolPanel.add(rectangle);
        toolPanel.add(ellipse);
        toolPanel.add(polygon);

        return toolPanel;
    }

    private JPanel createPanel(Color color){
        JPanel pnl = new JPanel();
        pnl.setBackground(color);
        return pnl;
    }

    private JRadioButton createRadioButton(String text, Color color, ActionListener actionListener){
        JRadioButton btn = new JRadioButton();
        btn.setBackground(color);
        btn.setText(text);
        btn.setSize(20,10);
        btn.addActionListener(actionListener);
        return btn;
    }
}
