package Commands;

import java.awt.*;

public class Plot extends Command {
    private Integer x, y;

    public Plot(int x, int y, Color penColor) {
        super(penColor, null, CommandEnum.PLOT);
        this.x = x;
        this.y =  y;
    }

    @Override
    public void addStartXPoint(int x) {
        this.x = x;
    }

    @Override
    public void addStartYPoint(int y) {
        this.y = y;
    }

    @Override
    public void addXPoint(int x) {

    }

    @Override
    public void addYPoint(int y) {

    }

    @Override
    public int getStartX() {
        return x;
    }

    @Override
    public int getStartY() {
        return y;
    }

    @Override
    public int getXPoint() {
        return 0;
    }

    @Override
    public int getYPoint() {
        return 0;
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
