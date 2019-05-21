package Commands;

import java.awt.*;

 abstract public class Command {
     private Color penColor, fillColor;
     private CommandType commandType;
     private int screenSize;

     /**
      * Generic command constructor
      * @param penColor the pen color of the command
      * @param fillColor the fill color of the command (if any)
      * @param commandType the type of command
      */
    public Command(Color penColor, Color fillColor, CommandType commandType, int screenSize){
        this.penColor = penColor;
        this.fillColor = fillColor;
        this.commandType = commandType;
        this.screenSize = screenSize;
    }

     /**
      * Sets the current screen size of the VECPanel
      * @param screenSize the screen size of the VECPanel
      */
    void setScreenSize(int screenSize){
        this.screenSize = screenSize;
    }

     /**
      * Returns the CommandType enum of the object
      * @return the command type of the object
      */
    public CommandType getCommandType(){
        return commandType;
    }

     /**
      * gets the pen color of the object
      * @return Color object of the Pen Color of the Command Object
      */
     public Color getPenColor() {
         return penColor;
     }

     /**
      * gets the fill color of the object
      * @return Color object of the Fill Color of the Command Object
      */
     public Color getFillColor() {
         return fillColor;
     }

     /**
      * Converts the Command Object to a VEC file ready string
      * @return A string of the Command Object converted to a VEC command
      */
     public abstract String toVEC();

     /**
      * Converts the Command Object to a string for display
      * @return A string of the Command Object
      */
     public String toString(){
         return toVEC();
     }

     /**
      * Scales the vecPanel coordinate to a vec coordinate 0.0-1.0
      * @param number The coordinate to convert
      * @return a double between 0.0 and 1.0
      */
     double IntToDouble(int number){
         double result = (double)number / screenSize;

         //ensure coordinate does not go out of bounds
         if(result > 1.0){
            result = 1.0;
         }else if(result < 0.0){
             result = 0.0;
         }

         return result;
     }

     /**
      * Converts the VEC coordinate to a int coordinate to display on the graphics panel
      * @param d The VEC coordinate to convert
      * @return Int of the coordinate
      */
     int DoubleToInt(double d){
         return (int) (d * screenSize);
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
      * Getter methods for drawing coordinates
      */
     public abstract int getStartX();

     public abstract int getStartY();

     public abstract int getXPoint();

     public abstract int getYPoint();

     /**
      * Draws the command on the supplied Graphics objects, and applies the screenSize of the VECPanel
      * @param graphics The Graphics object the command will be drawn on
      * @param screenSize The screenSize of the VECPanel
      */
     public abstract void draw(Graphics graphics, int screenSize);

     /**
      * returns whether the command has finished being drawn or not
      * @return Boolean whether the command is finished or not
      */
     public abstract boolean isCommandFinished();

     /**
      * sets the command as being finished drawing
      */
     public abstract void setCommandFinished();
}