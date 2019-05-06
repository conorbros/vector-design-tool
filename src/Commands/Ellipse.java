package Commands;

import java.awt.*;

public class Ellipse extends Command {

    public Ellipse(int x1, int y1, Color penColor, Color fillColor) {
        super(x1, y1, penColor, fillColor);
    }

    @Override
    public void draw(Graphics graphics) {
        if(getFillColor() != null){
            graphics.setColor(getFillColor());
            graphics.fillOval(getX1(), getY1(), getX2() - getX1(), getY2() - getY1());
        }
        graphics.setColor(getPenColor());
        graphics.drawOval(getX1(), getY1(), getX2() - getX1(), getY2() - getY1());
    }

    @Override
    public boolean isCommandFinished() {
        return false;
    }
}
