package Commands;

import java.awt.*;

public class Ellipse extends Command {

    public Ellipse(int x1, int y1, Color penColor, Color fillColor) {
        super(x1, y1, penColor, fillColor, CommandEnum.ELLIPSE);
    }

    @Override
    public void draw(Graphics graphics) {
        if(getFillColor() != null){
            graphics.setColor(getFillColor());
            graphics.fillOval(getStartX(), getStartY(), getXPoint() - getStartX(), getYPoint() - getStartY());
        }
        graphics.setColor(getPenColor());
        graphics.drawOval(getStartX(), getStartY(), getXPoint() - getStartX(), getYPoint() - getStartY());
    }

    @Override
    public boolean isCommandFinished() {
        return false;
    }

    @Override
    public void setCommandFinished() {

    }
}
