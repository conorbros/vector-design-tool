package Commands;

import java.awt.*;

public class Rectangle extends Command {

    public Rectangle(int x1, int y1, Color penColor, Color fillColor) {
        super(x1, y1, penColor, fillColor, CommandEnum.RECTANGLE);
    }

    @Override
    public void draw(Graphics graphics) {
        if(getFillColor() != null){
            graphics.setColor(getFillColor());
            graphics.fillRect(getStartX(), getStartY(), getXPoint()- getStartX(), getYPoint()- getStartY());
        }
        graphics.setColor(getPenColor());
        graphics.drawRect(getStartX(), getStartY(), getXPoint()- getStartX(), getYPoint()- getStartY());
    }

    @Override
    public boolean isCommandFinished() {
        return false;
    }
}
