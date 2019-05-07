package Commands;

import java.awt.*;

public class Plot extends Command {

    public Plot(int x, int y, Color penColor) {
        super(x, y, penColor, null, CommandEnum.PLOT);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getPenColor());
        graphics.drawLine(getStartX(), getStartY(), getStartX(), getYPoint());
    }

    @Override
    public boolean isCommandFinished() {
        return true;
    }

}
