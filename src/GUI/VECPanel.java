package GUI;

import Commands.*;
import Commands.Polygon;
import Commands.Rectangle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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

    public void setSelectedCommand(CommandType selectedCommand) {
        this.selectedCommand = selectedCommand;
    }

    public void setPenColor(Color penColor){
        this.penColor = penColor;
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    private void finishCommand(){
        drawnCommands.addCommand(currentCommand);
        currentCommand = null;
    }

    public void undoCommand(){
        if(drawnCommands.getLastCommand() != null){
            clearedCommands.addCommand(drawnCommands.getLastCommand());
            drawnCommands.removeLastCommand();
            repaint();
        }
    }

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
                    currentCommand = null;
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

        private void polygonHandler(MouseEvent e){
            if(e.getButton() == MouseEvent.BUTTON3 && currentCommand != null){
                currentCommand.setCommandFinished();
                finishCommand();
                return;
            }

            if(currentCommand != null){
                currentCommand.addXPoint(e.getX());
                currentCommand.addYPoint(e.getY());
            }else{
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
