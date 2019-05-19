package GUI;

import Commands.*;
import Commands.Polygon;
import Commands.Rectangle;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

import CommandList.CommandList;
import VecFile.VecFileException;

import static VecFile.VecFileInput.LoadVecFile;
import static VecFile.VecFileOutput.CommandsToExistingVecFile;
import static VecFile.VecFileOutput.CommandsToNewVecFile;

public class VECPanel extends JPanel {

    private CommandType selectedCommand;
    private Command currentCommand;
    private Color penColor;
    private Color fillColor;

    private CommandList drawnCommands;
    private CommandList clearedCommands;
    private File loadedFile;

    public VECPanel(){
        penColor = Color.BLACK;
        fillColor = null;
        drawnCommands = new CommandList();
        clearedCommands = new CommandList();
        loadedFile = null;

        setLayout(new BorderLayout());
        setSize(new Dimension(1000, 1000));
        setBackground(Color.WHITE);

        MouseController mouseController = new MouseController();
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        for(Command cmd : drawnCommands){
            cmd.draw(graphics);
        }

        if (currentCommand != null){
            currentCommand.draw(graphics);
        }
    }

    public void saveBeforeClosing() throws FileNotFoundException, VecFileException {
        if(drawnCommands.Count() != 0) {
            int saveFileResult = JOptionPane.showConfirmDialog(this, "The current file has not been saved, would you like to save it?", "Current file not saved", JOptionPane.YES_NO_OPTION);
            if (saveFileResult == JOptionPane.YES_OPTION) {
                if (loadedFile != null) {
                    saveFile();
                } else {
                    saveNewFile();
                }
            }
        }
    }

    public void newFile() throws FileNotFoundException, VecFileException {
        saveBeforeClosing();
        drawnCommands = new CommandList();
        clearedCommands = new CommandList();
        repaint();
    }

    public void openFile() throws FileNotFoundException, VecFileException {
        saveBeforeClosing();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("../"));
        chooser.setDialogTitle("Select a file to load.");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("VEC FILES", "vec");
        chooser.setFileFilter(filter);

        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File vecFile = chooser.getSelectedFile();
            LoadVecFile(vecFile, this);
            loadedFile = vecFile;
        }
    }

    public void loadCommandList(CommandList commands){
        clearedCommands = new CommandList();
        drawnCommands = commands;
        repaint();
    }

    public void saveFile() throws FileNotFoundException, VecFileException {
        if(loadedFile == null){
            JOptionPane.showMessageDialog(this,
                    "You must save this file as a new file.",
                    "This file was not imported",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            CommandsToExistingVecFile(drawnCommands, loadedFile);
        }

    }

    public void saveNewFile() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("../"));
        chooser.setDialogTitle("Select a directory to save the file in");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);


        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            String folderPath = String.valueOf(chooser.getSelectedFile());
            String fileName = getFileName();
            loadedFile = CommandsToNewVecFile(drawnCommands, folderPath, fileName);
        }

    }

    private String getFileName(){
        String fileName;
        boolean inputValid = false;

        do{
            fileName = JOptionPane.showInputDialog("Type a name for your file:");

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
    public void setSelectedCommand(CommandType selectedCommand) {
        this.selectedCommand = selectedCommand;
    }

    /**
     * sets the penColor
     * @param penColor the Color to set the penColor to
     */
    public void setPenColor(Color penColor){
        this.penColor = penColor;
    }

    /**
     * sets the fillColor
     * @param fillColor the Color to set the fillColor
     */
    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    /**
     * finishes the current command
     */
    private void finishCommand(){
        drawnCommands.addCommand(currentCommand);
        currentCommand.setCommandFinished();
        currentCommand = null;
        repaint();
    }

    /**
     * undoes the last drawn command by removing it from the drawnCommands and adding to the clearedCommands
     */
    public void undoCommand(){
        if(drawnCommands.getLastCommand() != null){
            clearedCommands.addCommand(drawnCommands.getLastCommand());
            drawnCommands.removeLastCommand();
            repaint();
        }
    }

    public void undoSelectedCommand(Command cmd){
        clearedCommands.addCommand(cmd);
        drawnCommands.removeCommand(cmd);
        repaint();
    }

    public void revertToSelectedCommand(Command cmd){
        clearedCommands.addAll(drawnCommands.getAllAfter(cmd));
        drawnCommands.removeAllAfter(cmd);
        repaint();
    }

    /**
     * redoes the last undone command by removing it from the clearedCommands and adding to the drawnCommands
     */
    public void redoCommand(){
        if(clearedCommands.getLastCommand() != null){
            drawnCommands.addCommand(clearedCommands.getLastCommand());
            clearedCommands.removeLastCommand();
            repaint();
        }
    }

    public void openUndoHistory() {
        JList undoHistoryList = new JList(drawnCommands.toArray());
        HistoryDialog undoDialog = new HistoryDialog("Select a command to undo:", undoHistoryList);
        undoDialog.setOnOk(e -> undoSelectedCommand(((Command)undoDialog.getSelectedItem())));
        undoDialog.setOnRevert(e -> revertToSelectedCommand(((Command)undoDialog.getSelectedItem())));
        undoDialog.show();
    }

    private class MouseController extends MouseAdapter{

        public void mousePressed(MouseEvent e){
            switch(selectedCommand){
                case PLOT:
                    currentCommand = new Plot(e.getX(), e.getY(), penColor);
                    finishCommand();
                    break;
                case LINE:
                    currentCommand = new Line(e.getX(), e.getY(), penColor);
                    break;
                case RECTANGLE:
                    currentCommand = new Rectangle(e.getX(), e.getY(), penColor, fillColor);
                    break;
                case ELLIPSE:
                    currentCommand = new Ellipse(e.getX(), e.getY(), penColor, fillColor);
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
                currentCommand.addXPoint(e.getX());
                currentCommand.addYPoint(e.getY());
                //if polygon is finished will set the current command in the vec window finished
            }else{
                //draw a new polygon if polygon is selected and there is not current command
                currentCommand = new Polygon(e.getX(), e.getY(), penColor, fillColor);
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
}
