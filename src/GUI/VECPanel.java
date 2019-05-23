package GUI;

import Commands.*;
import Commands.Polygon;
import Commands.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import CommandList.CommandList;
import Enums.CommandType;
import Enums.ImageType;
import VecFile.VecFileException;

import static VecFile.VecFileInput.LoadVecFile;
import static VecFile.VecFileOutput.CommandsToExistingVecFile;
import static VecFile.VecFileOutput.CommandsToNewVecFile;

public class VECPanel extends JPanel{

    private CommandType selectedCommand;
    private Command currentCommand;
    private Color penColor;
    private Color fillColor;
    private int screenSize;
    private CommandList drawnCommands;
    private CommandList clearedCommands;
    private CurrentFile currentFile;

    public VECPanel(){
        penColor = Color.BLACK;
        fillColor = null;
        drawnCommands = new CommandList();
        clearedCommands = new CommandList();
        currentFile = new CurrentFile(null, true, false);
        selectedCommand = CommandType.PLOT;

        setLayout(new BorderLayout());
        setSize(new Dimension(1000, 1000));
        setBackground(Color.WHITE);

        addComponentListener(new EventListener());
        MouseController mouseController = new MouseController();
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
        screenSize = 1000;
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        for(Command cmd : drawnCommands){
            cmd.draw(graphics, screenSize);
        }

        if (currentCommand != null){
            currentCommand.draw(graphics, screenSize);
        }
    }

    void saveBeforeClosing() throws FileNotFoundException{
        if(currentFile.isFileDirty()) {
            int saveFileResult = JOptionPane.showConfirmDialog(this, "The current file has not been saved, would you like to save it?", "Current file not saved", JOptionPane.YES_NO_OPTION);
            if (saveFileResult == JOptionPane.YES_OPTION) {
                if (!currentFile.isNewFile()) {
                    saveFile();
                } else {
                    saveNewFile();
                }
            }
        }
        revalidate();
    }

    void newFile() throws FileNotFoundException{
        saveBeforeClosing();
        drawnCommands = new CommandList();
        clearedCommands = new CommandList();
        currentFile = new CurrentFile(null, true, false);
        repaint();
        revalidate();
    }

    void openFile() throws FileNotFoundException{
        saveBeforeClosing();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("../"));
        chooser.setDialogTitle("Select a file to load.");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("VEC FILES", "vec");
        chooser.setFileFilter(filter);

        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File vecFile = chooser.getSelectedFile();
            LoadVecFile(vecFile, this, screenSize);
            currentFile = new CurrentFile(vecFile, false, false);
        }
        revalidate();
    }

    public void loadCommandList(CommandList commands){
        clearedCommands = new CommandList();
        drawnCommands = commands;
        repaint();
    }

    void saveFile() throws FileNotFoundException{
        if(currentFile.isNewFile()){
            saveNewFile();
        }else {
            CommandsToExistingVecFile(drawnCommands, currentFile.getFileObj());
            currentFile.setFileDirty(false);
        }
    }

    void saveNewFile() throws FileNotFoundException {
        String path = directoryChooser("Select a directory to save the file in", ".vec");
        if(path.equals("")) return;

        File file = CommandsToNewVecFile(drawnCommands, path);
        currentFile = new CurrentFile(file, false, false);
    }

    private String directoryChooser(String message, String fileEx){
        String folderPath;
        String fileName;

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("../"));
        chooser.setDialogTitle(message);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if(chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return "";

        do{
            folderPath = String.valueOf(chooser.getSelectedFile());
            fileName = getFileName();
        }while (!canWriteFile(folderPath, fileName, fileEx));

        revalidate();
        return folderPath + "\\" + fileName + fileEx;
    }

    private boolean canWriteFile(String folderPath, String fileName, String fileEx){
        File file = new File(folderPath, fileName + fileEx);

        if(file.exists()){
            int result = JOptionPane.showConfirmDialog(this,
                    "That file already exists, would you like to overwrite it",
                    "File already exists",
                    JOptionPane.YES_NO_OPTION);

            return result == JOptionPane.YES_OPTION;
        }
        return true;
    }

    private String getFileName(){
        String fileName;
        boolean inputValid = false;

        do{
            fileName = JOptionPane.showInputDialog(this, "Type a name for your file");
            if(isFilenameValid(fileName)){
                inputValid = true;
            }else{
                JOptionPane.showMessageDialog(this,
                        "Your file name is not valid, type another.",
                        "File name error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }while(!inputValid);
        return fileName;
    }

    private boolean isFilenameValid(String file) {
        File f = new File(file);
        try {
            f.getCanonicalPath();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * sets the selectedCommand
     * @param selectedCommand the CommandType to set the selected command to
     */
    void setSelectedCommand(CommandType selectedCommand) {
        this.selectedCommand = selectedCommand;
    }

    /**
     * sets the penColor
     * @param penColor the Color to set the penColor to
     */
    void setPenColor(Color penColor){
        this.penColor = penColor;
    }

    /**
     * sets the fillColor
     * @param fillColor the Color to set the fillColor
     */
    void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    /**
     * finishes the current command
     */
    private void finishCommand(){
        drawnCommands.addCommand(currentCommand);
        currentCommand.setCommandFinished();
        currentCommand = null;
        currentFile.setFileDirty(true);
        repaint();
    }

    /**
     * undoes the last drawn command by removing it from the drawnCommands and adding to the clearedCommands
     */
    void undoCommand(){
        if(drawnCommands.getLastCommand() != null){
            clearedCommands.addCommand(drawnCommands.getLastCommand());
            drawnCommands.removeLastCommand();
            currentFile.setFileDirty(true);
            repaint();
        }
    }

    private void undoSelectedCommand(Command cmd){
        clearedCommands.addCommand(cmd);
        drawnCommands.removeCommand(cmd);
        currentFile.setFileDirty(true);
        repaint();
    }

    /**
     * Reverts the drawn commands back to the supplied command by removing them from the drawn commands list and adds them to the cleared commands list
     * @param cmd the command that the file will be reverted to
     */
    private void revertToSelectedCommand(Command cmd){
        clearedCommands.addAll(drawnCommands.getAllAfter(cmd));
        drawnCommands.removeAllAfter(cmd);
        currentFile.setFileDirty(true);
        repaint();
    }

    /**
     * redoes the last undone command by removing it from the clearedCommands and adding to the drawnCommands
     */
    void redoCommand(){
        if(clearedCommands.getLastCommand() != null){
            drawnCommands.addCommand(clearedCommands.getLastCommand());
            clearedCommands.removeLastCommand();
            currentFile.setFileDirty(true);
            repaint();
        }
    }

    void openUndoHistory() {
        JList undoHistoryList = new JList(drawnCommands.toArray());
        HistoryDialog undoDialog = new HistoryDialog("Commands are shown in ascending order. Select a command to undo:", undoHistoryList);
        undoDialog.setOnOk(e -> undoSelectedCommand(((Command)undoDialog.getSelectedItem())));
        undoDialog.setOnRevert(e -> revertToSelectedCommand(((Command)undoDialog.getSelectedItem())));
        undoDialog.show();
    }

    void exportImage(ImageType imageType) {
        BufferedImage image;
        Graphics graphics;
        String fileEx = "";

        switch(imageType){
            case PNG:
                fileEx = "png";
                break;
            case JPEG:
                fileEx = "jpeg";
                break;
            case BMP:
                fileEx = "bmp";
        }
        String path = directoryChooser("Select a folder to export the file to", "." + fileEx);
        int size = getImageDimension();
        if (size == -1) return;
        image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
        paintComponent(graphics);
        graphics.dispose();
        try {
            ImageIO.write(image, fileEx, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        revalidate();
    }

    private int getImageDimension(){
        Object[] options = {"Custom dimension", "Use current window dimension", "Cancel"};
        JPanel pnl = new JPanel();
        pnl.add(new JLabel("Enter the dimension you would like for the export image or export the image as the current window size."));
        JTextField textField = new JTextField(10);
        pnl.add(textField);

        boolean invalidInput = false;
        do{
            int result = JOptionPane.showOptionDialog(null, pnl, "Enter a Number",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null);

            if(result == JOptionPane.YES_OPTION){
                int input = Integer.parseInt(textField.getText());
                if(input < 0 || input > 1000){
                    JOptionPane.showMessageDialog(this, "Invalid dimension.");
                    invalidInput = true;
                }else{
                    return input;
                }
            }else if(result == JOptionPane.NO_OPTION){
                return screenSize;
            }else if(result == JOptionPane.CANCEL_OPTION){
                return -1;
            }
        }while(invalidInput);

        return -1;
    }

    private class MouseController extends MouseAdapter{

        public void mousePressed(MouseEvent e){
            switch(selectedCommand){
                case PLOT:
                    currentCommand = new Plot(e.getX(), e.getY(), penColor, screenSize);
                    finishCommand();
                    break;
                case LINE:
                    currentCommand = new Line(e.getX(), e.getY(), penColor, screenSize);
                    break;
                case RECTANGLE:
                    currentCommand = new Rectangle(e.getX(), e.getY(), penColor, fillColor, screenSize);
                    break;
                case ELLIPSE:
                    currentCommand = new Ellipse(e.getX(), e.getY(), penColor, fillColor, screenSize);
                    break;
                case POLYGON:
                    polygonHandler(e);
                    break;
            }
            repaint();
        }

        /**
         * handler method for polygon
         * @param e the MouseEvent pressed
         */
        private void polygonHandler(MouseEvent e){
            //finish the poly if the mouse is right clicked
            if(e.getButton() == MouseEvent.BUTTON3 && currentCommand == null){
                return;
            }else if(e.getButton() == MouseEvent.BUTTON3){
                finishCommand();
                return;
            }

            //create a new polygon if currentCommand is null
            if(currentCommand != null && !currentCommand.isCommandFinished()) {

                int startX = currentCommand.getStartX();
                int startY = currentCommand.getStartY();
                double distance = Math.hypot((double)startX - (double)e.getX(), (double)startY - (double)e.getY());

                if(distance < 10 && distance > -10){
                    finishCommand();
                }else{
                    currentCommand.addXPoint(e.getX());
                    currentCommand.addYPoint(e.getY());
                }
                //if polygon is finished will set the current command in the vec window finished
            }else{
                //draw a new polygon if polygon is selected and there is not current command
                currentCommand = new Polygon(e.getX(), e.getY(), penColor, fillColor, screenSize);
            }
        }

        public void mouseReleased(MouseEvent e){
            if(currentCommand != null && !currentCommand.isCommandFinished()) {
                if (currentCommand.getCommandType() == CommandType.LINE || currentCommand.getCommandType() == CommandType.RECTANGLE || currentCommand.getCommandType() == CommandType.ELLIPSE) {
                    currentCommand.addXPoint(e.getX());
                    currentCommand.addYPoint(e.getY());
                    finishCommand();
                }
                repaint();
            }
        }

        public void mouseDragged(MouseEvent e){
            if(currentCommand != null && currentCommand.getCommandType() != CommandType.POLYGON){
                currentCommand.addXPoint(e.getX());
                currentCommand.addYPoint(e.getY());
            }
            repaint();
        }
    }

    private class EventListener implements ComponentListener{

        @Override
        public void componentResized(ComponentEvent e) {
            java.awt.Rectangle b = e.getComponent().getBounds();
            int side;
            if(b.width < b.height){
                side = b.width;
            }else{
                side = b.height;
            }
            screenSize = side-1;
            e.getComponent().setBounds(b.x, b.y, side, side);
            repaint();
        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    }
}
