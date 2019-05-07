package Commands;

import java.awt.*;

 abstract public class Command {
     private int x1, y1, x2, y2;
     private Color penColor, fillColor;
     private CommandEnum commandType;

     /**
      * Generic command constructor
      * @param x1 the starting x position
      * @param y1 the starting y position
      * @param penColor the pen color of the command
      * @param fillColor the fill color of the command (if any)
      * @param commandType the type of command
      */
    public Command(int x1, int y1, Color penColor, Color fillColor, CommandEnum commandType){
        this.penColor = penColor;
        this.fillColor = fillColor;
        this.x1 = x1;
        this.y1 = y1;
        this.commandType = commandType;
    }

     /**
      *
      * @return the command type of the object
      */
    public CommandEnum getCommandType(){
        return commandType;
    }

     /**
      * sets the start x point of the command object
      * @param x the new start x point
      */
     public void addStartXPoint(int x){
        this.x1 = x;
     }

     /**
      * sets the start y point of the command object
      * @param y
      */
     public void addStartYPoint(int y){
         this.y1 = y;
     }

     /**
      * adds an x point to the command
      * @param x the x point to add
      */
     public void addXPoint(int x){
         this.x2 = x;
     }

     /**
      * adds a y point to the command
      * @param y the y point to add
      */
     public void addYPoint(int y){
         this.y2 = y;
     }

     /**
      * Getter methods
      */

     public int getStartX(){
         return x1;
     }

     public int getStartY(){
         return y1;
     }

     public int getXPoint(){
         return x2;
     }

     public int getYPoint(){
         return  y2;
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

     public abstract void draw(Graphics graphics);

     public abstract boolean isCommandFinished();
}