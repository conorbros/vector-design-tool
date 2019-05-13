package GUI;

import Commands.*;
import Commands.Polygon;
import Commands.Rectangle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import CommandList.CommandList;

public class VECPanel extends JPanel {

    private CommandType selectedCommand;
    private Command currentCommand;
    private Color penColor;
    private Color fillColor;

    private CommandList drawnCommands;
    private CommandList clearedCommands;

    public VECPanel(){
        penColor = Color.BLACK;
        fillColor = null;
        drawnCommands = new CommandList();
        clearedCommands = new CommandList();

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
            if(e.getButton() == MouseEvent.BUTTON3 && currentCommand != null){
                currentCommand.setCommandFinished();
                finishCommand();
                return;
            }

            //create a new polygon if currentCommand is null
            if(currentCommand != null && !currentCommand.isCommandFinished()) {
                currentCommand.addXPoint(e.getX());
                currentCommand.addYPoint(e.getY());
                //if polygon is finished will set the current command in the vec window finished
            }else if (currentCommand != null){
                finishCommand();
            }else{
                //draw a new polygon if polygon is selected and there is not current command
                currentCommand = new Polygon(e.getX(), e.getY(), penColor, fillColor);
            }
        }

        public void mouseReleased(MouseEvent e){
            if(currentCommand != null) {
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
