package Commands;

import java.awt.*;

public class Line extends Command {

    public Line(int x1, int y1, Color penColor, Color fillColor) {
        super(x1, y1, penColor, fillColor, CommandEnum.LINE);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getPenColor());
        graphics.drawLine(getStartX(), getStartY(), getXPoint(), getYPoint());
    }

    @Override
    public boolean isCommandFinished() {
        return false;
    }
}
