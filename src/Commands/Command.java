package Commands;

import java.awt.*;

 abstract public class Command {
     private Color penColor;
     private Color fillColor;
     private CommandEnum commandType;

     /**
      * Generic command constructor
      * @param penColor the pen color of the command
      * @param fillColor the fill color of the command (if any)
      * @param commandType the type of command
      */
    public Command(Color penColor, Color fillColor, CommandEnum commandType){
        this.penColor = penColor;
        this.fillColor = fillColor;
        this.commandType = commandType;
    }

     /**
      *
      * @return the command type of the object
      */
    public CommandEnum getCommandType(){
        return commandType;
    }

     public Color getPenColor() {
         return penColor;
     }

     public void setPenColor(Color penColor) {
         this.penColor = penColor;
     }

     public Color getFillColor() {
         return fillColor;
     }

     public void setFillColor(Color fillColor) {
         this.fillColor = fillColor;
     }

     /**
      * sets the start x point of the command object
      * @param x the new start x point
      */
     public abstract void addStartXPoint(int x);

     /**
      * sets the start y point of the command object
      * @param y
      */
     public abstract void addStartYPoint(int y);

     /**
      * adds an x point to the command
      * @param x the x point to add
      */
     public abstract void addXPoint(int x);
     /**
      * adds a y point to the command
      * @param y the y point to add
      */
     public abstract void addYPoint(int y);

     /**
      * Getter methods
      */

     public abstract int getStartX();

     public abstract int getStartY();

     public abstract int getXPoint();

     public abstract int getYPoint();

     public abstract void draw(Graphics graphics);

     public abstract boolean isCommandFinished();

     public abstract void setCommandFinished();
}