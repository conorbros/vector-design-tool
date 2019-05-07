package Commands;

import java.awt.*;

public class Line extends Command {
    private Integer x1, y1, x2, y2;
    private boolean commandFinished;

    public Line(int x, int y, Color penColor) {
        super(penColor, null, CommandEnum.LINE);
        this.x1 = x;
        this.y1 = y;
        commandFinished = false;
    }

    @Override
    public void addStartXPoint(int x) {
        this.x1 = x;
    }

    @Override
    public void addStartYPoint(int y) {
        this.y1 = y;
    }

    @Override
    public void addXPoint(int x) {
        this.x2 = x;
    }

    @Override
    public void addYPoint(int y) {
        this.y2 = y;
    }

    @Override
    public int getStartX() {
        return x1;
    }

    @Override
    public int getStartY() {
        return y1;
    }

    @Override
    public int getXPoint() {
        return x2;
    }

    @Override
    public int getYPoint() {
        return y2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getPenColor());
        graphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public boolean isCommandFinished() {
        return commandFinished;
    }

    @Override
    public void setCommandFinished() {
        if(x2 != null && y2 != null) commandFinished = true;
    }
}
