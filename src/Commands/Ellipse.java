package Commands;

import java.awt.*;

public class Ellipse extends Command {
    private Integer x1, y1, x2, y2;
    private boolean commandFinished;

    public Ellipse(int x1, int y1, Color penColor, Color fillColor) {
        super(penColor, fillColor, CommandEnum.ELLIPSE);
        this.x1 = x1;
        this.y1 = y1;
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
        return 0;
    }

    @Override
    public int getStartY() {
        return 0;
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
        if(getFillColor() != null){
            graphics.setColor(getFillColor());
            graphics.fillOval(x1, y1, x2-x1, y2-y1);
        }
        graphics.setColor(getPenColor());
        graphics.drawOval(x1, y1, x2-x1, y2-y1);
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
