package Commands;

import VecFile.VecFileException;

import java.awt.*;

 abstract public class Command {
     private Color penColor;
     private Color fillColor;
     private CommandType commandType;
     private static final int screenSize = 1000;


     public Command(){

     }

     /**
      * Generic command constructor
      * @param penColor the pen color of the command
      * @param fillColor the fill color of the command (if any)
      * @param commandType the type of command
      */
    public Command(Color penColor, Color fillColor, CommandType commandType){
        this.penColor = penColor;
        this.fillColor = fillColor;
        this.commandType = commandType;
    }

     /**
      *
      * @return the command type of the object
      */
    public CommandType getCommandType(){
        return commandType;
    }

     /**
      * gets the pen color of the object
      * @return
      */
     public Color getPenColor() {
         return penColor;
     }

     /**
      * sets the pen color of the command object
      * @param penColor
      */
     public void setPenColor(Color penColor) {
         this.penColor = penColor;
     }

     /**
      * gets the fill color of the object
      * @return
      */
     public Color getFillColor() {
         return fillColor;
     }

     public void setFillColor(Color fillColor) {
         this.fillColor = fillColor;
     }


     public abstract String toVEC();

     public String toString(){
         return toVEC();
     }

     /**
      * Scales the vecPanel coordinate to a vec coordinate 0.0-1.0
      * @param number The coordinate to convert
      * @return a double between 0.0 and 1.0
      */
     public double IntToDecimalConvert(int number){
         double result = (double)number / screenSize;

         if(result > 1.0 || result < 0.0) try {
             throw new VecFileException("Invalid coordinate.");
         } catch (VecFileException e) {
             e.printStackTrace();
         }

         return result;
     }

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

     /**
      * draws the command as it currently is on the graphics componenet provided
      * @param graphics
      */
     public abstract void draw(Graphics graphics);

     /**
      * returns whether the command has finished being drawn or not
      * @return
      */
     public abstract boolean isCommandFinished();

     /**
      * sets the command as being finished drawing
      */
     public abstract void setCommandFinished();
}