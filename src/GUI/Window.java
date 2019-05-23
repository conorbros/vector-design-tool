package GUI;

import Enums.CommandType;
import Enums.ImageType;
import VecFile.VecFileException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;


public class Window extends JFrame implements ActionListener, Runnable{
    private static final int WIDTH = 1030;
    private static final int HEIGHT = 1000;
    private static final int MIN_WIDTH = 530;
    private static final int MIN_HEIGHT = 870;
    private MenuHandler menuHandler = new MenuHandler();

    private VECPanel vecPanel = new VECPanel();

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Window("Vector Design Tool"));
    }

    protected Window(String title) throws HeadlessException{
        super(title);
        createGUI();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    vecPanel.saveBeforeClosing();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
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

        JPanel container = new JPanel(new BorderLayout());
        container.add(vecPanel, BorderLayout.CENTER);

        add(container, BorderLayout.CENTER);
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
        menuBar.add(imageExportMenu());

        return menuBar;
    }

    private JMenu fileMenu() {
        JMenu fileMenu = new JMenu("File");

        ImageIcon newIcon = new ImageIcon(getClass().getResource("icons/new.png"));
        ImageIcon openIcon = new ImageIcon(getClass().getResource("icons/open.png"));
        ImageIcon saveIcon = new ImageIcon(getClass().getResource("icons/save.png"));
        ImageIcon saveNewIcon = new ImageIcon(getClass().getResource("icons/saveNew.png"));
        ImageIcon exitIcon = new ImageIcon(getClass().getResource("icons/exit.png"));

        JMenuItem newFile = new JMenuItem("New", newIcon);
        JMenuItem openFile = new JMenuItem("Open", openIcon);
        JMenuItem saveFile = new JMenuItem("Save", saveIcon);
        JMenuItem saveNewFile = new JMenuItem("Save As New", saveNewIcon);
        JMenuItem exitFile = new JMenuItem("Exit", exitIcon);

        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        newFile.addActionListener(e -> {
            try {
                vecPanel.newFile();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        openFile.addActionListener(e -> {
            try {
                vecPanel.openFile();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

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

        exitFile.addActionListener(e -> {
            try {
                exitProgram();
            } catch (FileNotFoundException | VecFileException ex) {
                ex.printStackTrace();
            }
        });

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveNewFile);
        fileMenu.add(exitFile);
        fileMenu.addMenuListener(menuHandler);
        return fileMenu;
    }

    private void exitProgram() throws FileNotFoundException, VecFileException {
        vecPanel.saveBeforeClosing();
        System.exit(0);
    }


    private JMenu commmandMenu(){
        JMenu commandMenu = new JMenu("Commands");

        ImageIcon plotIcon = new ImageIcon(getClass().getResource("icons/plot.png"));
        ImageIcon lineIcon = new ImageIcon(getClass().getResource("icons/line.png"));
        ImageIcon rectangleIcon = new ImageIcon(getClass().getResource("icons/rectangle.png"));
        ImageIcon ellipseIcon = new ImageIcon(getClass().getResource("icons/ellipse.png"));
        ImageIcon polygonIcon = new ImageIcon(getClass().getResource("icons/polygon.png"));

        JMenuItem plot = new JMenuItem("PLOT", plotIcon);
        JMenuItem line = new JMenuItem("LINE", lineIcon);
        JMenuItem rectangle = new JMenuItem("RECTANGLE", rectangleIcon);
        JMenuItem ellipse = new JMenuItem("ELLIPSE", ellipseIcon);
        JMenuItem polygon = new JMenuItem("POLYGON", polygonIcon);

        plot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        line.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        rectangle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        ellipse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        polygon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));

        plot.setMnemonic('T');
        line.setMnemonic('L');
        rectangle.setMnemonic('R');
        ellipse.setMnemonic('E');
        polygon.setMnemonic('G');

        commandMenu.add(plot);
        commandMenu.add(line);
        commandMenu.add(rectangle);
        commandMenu.add(ellipse);
        commandMenu.add(polygon);

        plot.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.PLOT));
        line.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.LINE));
        rectangle.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.RECTANGLE));
        ellipse.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.ELLIPSE));
        polygon.addActionListener(e -> vecPanel.setSelectedCommand(CommandType.POLYGON));
        commandMenu.addMenuListener(menuHandler);
        return commandMenu;
    }

    private JMenu colorMenu(){
        JMenu colorMenu = new JMenu("Colors");

        ImageIcon penIcon = new ImageIcon(getClass().getResource("icons/pen.png"));
        ImageIcon fillIcon = new ImageIcon(getClass().getResource("icons/fill.png"));

        JMenuItem pen = new JMenuItem("Pen Color", penIcon);
        JMenuItem fill = new JMenuItem("Fill Color", fillIcon);

        pen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        fill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

        pen.setMnemonic('P');
        fill.setMnemonic('F');

        colorMenu.add(pen);
        colorMenu.add(fill);

        pen.addActionListener(e -> vecPanel.setPenColor(JColorChooser.showDialog(null, "Choose a color", null)));
        fill.addActionListener(e -> vecPanel.setFillColor(JColorChooser.showDialog(null, "Choose a color", null)));
        colorMenu.addMenuListener(menuHandler);
        return colorMenu;
    }

    private JMenu historyMenu(){
        JMenu historyMenu = new JMenu("History");

        ImageIcon undoIcon = new ImageIcon(getClass().getResource("icons/undo.png"));
        ImageIcon redoIcon = new ImageIcon(getClass().getResource("icons/redo.png"));
        ImageIcon undoHistIcon = new ImageIcon(getClass().getResource("icons/undoHist.png"));

        JMenuItem undo = new JMenuItem("undo", undoIcon);
        JMenuItem redo = new JMenuItem("redo", redoIcon);
        JMenuItem undoHistory = new JMenuItem("undo history", undoHistIcon);

        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        undoHistory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        historyMenu.add(undo);
        historyMenu.add(redo);
        historyMenu.add(undoHistory);

        undo.addActionListener(e -> vecPanel.undoCommand());
        redo.addActionListener(e -> vecPanel.redoCommand());
        undoHistory.addActionListener(e -> vecPanel.openUndoHistory());

        historyMenu.addMenuListener(menuHandler);
        return historyMenu;
    }

    private JMenu imageExportMenu(){
        JMenu imageExportMenu = new JMenu("Export");

        JMenuItem png = new JMenuItem("Export to PNG");
        JMenuItem jpeg = new JMenuItem("Export To JPEG");
        JMenuItem bmp = new JMenuItem("Export to BMP");

        imageExportMenu.add(png);
        imageExportMenu.add(jpeg);
        imageExportMenu.add(bmp);

        png.addActionListener(e -> vecPanel.exportImage(ImageType.PNG));
        jpeg.addActionListener(e -> vecPanel.exportImage(ImageType.JPEG));
        bmp.addActionListener(e -> vecPanel.exportImage(ImageType.BMP));

        imageExportMenu.addMenuListener(menuHandler);
        return imageExportMenu;
    }

    private JPanel toolPanel(){
        JPanel toolPanel = createPanel(Color.LIGHT_GRAY);
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));

        ImageIcon penIcon = new ImageIcon(getClass().getResource("icons/pen.png"));
        ImageIcon fillIcon = new ImageIcon(getClass().getResource("icons/fill.png"));

        ButtonGroup penColors = new ButtonGroup();
        JRadioButton penBlack = createRadioButton("                         ", Color.BLACK, e -> vecPanel.setPenColor(Color.BLACK), penIcon);
        penBlack.setSelected(true);
        JRadioButton penRed = createRadioButton("                         ", Color.RED, e -> vecPanel.setPenColor(Color.RED), penIcon);
        JRadioButton penOrange = createRadioButton("                         ", Color.ORANGE, e -> vecPanel.setPenColor(Color.ORANGE), penIcon);
        JRadioButton penYellow = createRadioButton("                         ", Color.YELLOW, e -> vecPanel.setPenColor(Color.YELLOW), penIcon);
        JRadioButton penGreen = createRadioButton("                         ", Color.GREEN, e -> vecPanel.setPenColor(Color.GREEN), penIcon);
        JRadioButton penBlue = createRadioButton("                         ", Color.BLUE, e -> vecPanel.setPenColor(Color.BLUE), penIcon);

        penColors.add(penBlack);
        penColors.add(penRed);
        penColors.add(penOrange);
        penColors.add(penYellow);
        penColors.add(penGreen);
        penColors.add(penBlue);

        JPanel penPanel = createPanel(Color.LIGHT_GRAY);
        BoxLayout penLayout = new BoxLayout(penPanel, BoxLayout.Y_AXIS);
        penPanel.setLayout(penLayout);
        TitledBorder penTitle = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Pen Colour");
        penTitle.setTitleJustification(TitledBorder.LEFT);
        penPanel.setBorder(penTitle);

        penPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        penPanel.add(penBlack);
        penPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        penPanel.add(penRed);
        penPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        penPanel.add(penOrange);
        penPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        penPanel.add(penYellow);
        penPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        penPanel.add(penGreen);
        penPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        penPanel.add(penBlue);
        penPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        ButtonGroup fillColors = new ButtonGroup();
        JRadioButton fillNull = createRadioButton("no fill               ", Color.LIGHT_GRAY, e -> vecPanel.setFillColor(null), fillIcon);
        fillNull.setSelected(true);
        JRadioButton fillWhite = createRadioButton("                         ", Color.WHITE, e -> vecPanel.setFillColor(Color.WHITE), fillIcon);
        JRadioButton fillRed = createRadioButton("                         ", Color.RED, e -> vecPanel.setFillColor(Color.RED), fillIcon);
        JRadioButton fillOrange = createRadioButton("                         ", Color.ORANGE, e -> vecPanel.setFillColor(Color.ORANGE), fillIcon);
        JRadioButton fillYellow = createRadioButton("                         ", Color.YELLOW, e -> vecPanel.setFillColor(Color.YELLOW), fillIcon);
        JRadioButton fillGreen = createRadioButton("                         ", Color.GREEN, e -> vecPanel.setFillColor(Color.GREEN), fillIcon);
        JRadioButton fillBlue = createRadioButton("                         ", Color.BLUE, e -> vecPanel.setFillColor(Color.BLUE), fillIcon);

        fillColors.add(fillNull);
        fillColors.add(fillWhite);
        fillColors.add(fillRed);
        fillColors.add(fillOrange);
        fillColors.add(fillYellow);
        fillColors.add(fillGreen);
        fillColors.add(fillBlue);

        JPanel fillPanel = createPanel(Color.LIGHT_GRAY);
        BoxLayout fillLayout = new BoxLayout(fillPanel, BoxLayout.Y_AXIS);
        fillPanel.setLayout(fillLayout);
        TitledBorder fillTitle = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Fill Colour");
        fillTitle.setTitleJustification(TitledBorder.LEFT);
        fillPanel.setBorder(fillTitle);

        fillPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        fillPanel.add(fillNull);
        fillPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        fillPanel.add(fillWhite);
        fillPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        fillPanel.add(fillRed);
        fillPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        fillPanel.add(fillOrange);
        fillPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        fillPanel.add(fillYellow);
        fillPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        fillPanel.add(fillGreen);
        fillPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        fillPanel.add(fillBlue);
        fillPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        ImageIcon plotIcon = new ImageIcon(getClass().getResource("icons/plot.png"));
        ImageIcon lineIcon = new ImageIcon(getClass().getResource("icons/line.png"));
        ImageIcon rectangleIcon = new ImageIcon(getClass().getResource("icons/rectangle.png"));
        ImageIcon ellipseIcon = new ImageIcon(getClass().getResource("icons/ellipse.png"));
        ImageIcon polygonIcon = new ImageIcon(getClass().getResource("icons/polygon.png"));

        ButtonGroup commands = new ButtonGroup();
        JRadioButton plot = createRadioButton("PLOT", Color.LIGHT_GRAY, e -> vecPanel.setSelectedCommand(CommandType.PLOT), plotIcon);
        plot.setSelected(true);
        JRadioButton line = createRadioButton("LINE", Color.LIGHT_GRAY, e -> vecPanel.setSelectedCommand(CommandType.LINE), lineIcon);
        JRadioButton rectangle = createRadioButton("RECTANGLE", Color.LIGHT_GRAY, e -> vecPanel.setSelectedCommand(CommandType.RECTANGLE), rectangleIcon);
        JRadioButton ellipse = createRadioButton("ELLIPSE", Color.LIGHT_GRAY, e -> vecPanel.setSelectedCommand(CommandType.ELLIPSE), ellipseIcon);
        JRadioButton polygon = createRadioButton("POLYGON", Color.LIGHT_GRAY, e -> vecPanel.setSelectedCommand(CommandType.POLYGON), polygonIcon);
        commands.add(plot);
        commands.add(line);
        commands.add(rectangle);
        commands.add(ellipse);
        commands.add(polygon);

        JPanel commandsPanel = createPanel(Color.LIGHT_GRAY);
        BoxLayout commandsLayout = new BoxLayout(commandsPanel, BoxLayout.Y_AXIS);
        commandsPanel.setLayout(commandsLayout);
        TitledBorder commandsTitle = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Commands");
        commandsTitle.setTitleJustification(TitledBorder.LEFT);
        commandsPanel.setBorder(commandsTitle);

        commandsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        commandsPanel.add(plot);
        commandsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        commandsPanel.add(line);
        commandsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        commandsPanel.add(rectangle);
        commandsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        commandsPanel.add(ellipse);
        commandsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        commandsPanel.add(polygon);
        commandsPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        toolPanel.add(penPanel);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        toolPanel.add(fillPanel);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        toolPanel.add(commandsPanel);

        return toolPanel;
    }

    private JPanel createPanel(Color color){
        JPanel pnl = new JPanel();
        pnl.setBackground(color);
        return pnl;
    }

    private JRadioButton createRadioButton(String text, Color color, ActionListener actionListener, Icon icon){
        JRadioButton btn = new JRadioButton();
        btn.setBackground(color);
        btn.setText(text);
        btn.setSize(20,10);
        btn.addActionListener(actionListener);
        btn.setIcon(icon);
        return btn;
    }

    private class MenuHandler implements MenuListener{

        @Override
        public void menuSelected(MenuEvent e) {
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            revalidate();
            vecPanel.revalidate();
        }

        @Override
        public void menuCanceled(MenuEvent e) {

        }
    }
}