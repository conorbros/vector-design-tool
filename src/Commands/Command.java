package Commands;

import java.awt.*;

 abstract public class Command {
     private int x1, y1, x2, y2;
     private Color penColor, fillColor;

    public Command(int x1, int y1, Color penColor, Color fillColor){
        this.penColor = penColor;
        this.fillColor = fillColor;
        this.x1 = x1;
        this.y1 = y1;
    }

     public void setX1(int x1){
         this.x1 = x1;
     }

     public void setY1(int y1){
         this.y1 = y1;
     }

     public void setX2(int x2){
         this.x2 = x2;
     }

     public void setY2(int y2){
         this.y2 = y2;
     }

     /**
      * Getter methods
      */

     public int getX1(){
         return x1;
     }

     public int getY1(){
         return y1;
     }

     public int getX2(){
         return x2;
     }

     public int getY2(){
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