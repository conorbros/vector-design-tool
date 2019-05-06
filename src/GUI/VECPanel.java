package GUI;

import Commands.*;
import Commands.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VECPanel extends JPanel {

    private CommandEnum selectedCommand;
    private Command currentCommand;
    private Color penColor;
    private Color fillColor;

    public VECPanel(){
        penColor = Color.BLACK;
        fillColor = null;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        MouseController mouseController = new MouseController();
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        if (currentCommand != null){
            currentCommand.draw(graphics);
        }
    }

    public void setSelectedCommand(CommandEnum selectedCommand) {
        this.selectedCommand = selectedCommand;
    }

    public void setPenColor(Color penColor){
        this.penColor = penColor;
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    private void clearCommand(){
        currentCommand = null;
    }

    private class MouseController extends MouseAdapter{

        public void mousePressed(MouseEvent e){
            switch(selectedCommand){
                case PLOT:
                    currentCommand = new Plot(e.getX(), e.getY(), penColor);
                    currentCommand = null;
                    repaint();
                    break;
                case LINE:
                    currentCommand = new Line(e.getX(), e.getY(), penColor, fillColor);
                    break;
                case RECTANGLE:
                    currentCommand = new Rectangle(e.getX(), e.getY(), penColor, fillColor);
                    break;
                case ELLIPSE:
                    currentCommand = new Ellipse(e.getX(), e.getY(), penColor, fillColor);
                    break;
                case POLYGON:

                    break;
            }
        }

        public void mouseReleased(MouseEvent e){
            if(selectedCommand == CommandEnum.LINE || selectedCommand == CommandEnum.RECTANGLE || selectedCommand == CommandEnum.ELLIPSE){
                currentCommand.setX2(e.getX());
                currentCommand.setY2(e.getY());
            }
            clearCommand();
            repaint();
        }

        public void mouseDragged(MouseEvent e){
            if(currentCommand != null){
                currentCommand.setX2(e.getX());
                currentCommand.setY2(e.getY());
                repaint();
            }
        }
    }
}
