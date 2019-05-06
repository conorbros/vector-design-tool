package Commands;

import java.awt.*;

public class Plot extends Command {

    public Plot(int x, int y, Color penColor) {
        super(x, y, penColor, null);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getPenColor());
        graphics.drawLine(getX1(), getY1(), getX1(), getY2());
    }

    @Override
    public boolean isCommandFinished() {
        return true;
    }

}
