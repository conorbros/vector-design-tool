package Commands;

import java.awt.*;

public class Plot extends Command {
    private boolean commandFinished;

    public Plot(int x, int y, Color penColor) {
        super(x, y, penColor, null, CommandEnum.PLOT);
        commandFinished = true;
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

    @Override
    public void setCommandFinished() {

    }


}
