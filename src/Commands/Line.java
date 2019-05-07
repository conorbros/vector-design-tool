package Commands;

import java.awt.*;

public class Line extends Command {
    private boolean commandFinished;

    public Line(int x1, int y1, Color penColor, Color fillColor) {
        super(x1, y1, penColor, fillColor, CommandEnum.LINE);
        commandFinished = false;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getPenColor());
        graphics.drawLine(getStartX(), getStartY(), getXPoint(), getYPoint());
    }

    @Override
    public boolean isCommandFinished() {
        return commandFinished;
    }

    @Override
    public void setCommandFinished() {
        if(getXPoint() != 0 && getYPoint() != 0) commandFinished = true;
    }
}
