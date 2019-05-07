package GUI;

import Commands.CommandEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        JRadioButton fillWhite = createRadioButton("", Color.WHITE, e -> vecPanel.setFillColor(Color.WHITE));
        fillWhite.setSelected(true);
        JRadioButton fillRed = createRadioButton("", Color.RED, e -> vecPanel.setFillColor(Color.RED));
        JRadioButton fillBlue = createRadioButton("", Color.BLUE, e -> vecPanel.setFillColor(Color.BLUE));
        fillColors.add(fillWhite);
        fillColors.add(fillRed);
        fillColors.add(fillBlue);

        toolPanel.add(fillLabel);
        toolPanel.add(fillWhite);
        toolPanel.add(fillRed);
        toolPanel.add(fillBlue);

        ButtonGroup commands = new ButtonGroup();
        JLabel commandsLabel = new JLabel("Commands:");
        JRadioButton plot = createRadioButton("PLOT", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandEnum.PLOT));
        plot.setSelected(true);
        JRadioButton line = createRadioButton("LINE", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandEnum.LINE));
        JRadioButton rectangle = createRadioButton("RECTANGLE", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandEnum.RECTANGLE));
        JRadioButton ellipse = createRadioButton("ELLIPSE", Color.WHITE, e -> vecPanel.setSelectedCommand(CommandEnum.ELLIPSE));
        commands.add(plot);
        commands.add(line);
        commands.add(rectangle);
        commands.add(ellipse);

        toolPanel.add(commandsLabel);
        toolPanel.add(plot);
        toolPanel.add(line);
        toolPanel.add(rectangle);
        toolPanel.add(ellipse);

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
