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
    public void addXPoint(int x) {
        throw new CommandException(CommandEnum.PLOT, "cannot add more than one point");
    }

    @Override
    public void addYPoint(int y) {
        throw new CommandException(CommandEnum.PLOT, "cannot add more than one point");
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
        throw new CommandException(CommandEnum.PLOT, "cannot have more than one point");
    }

    @Override
    public int getYPoint() {
        throw new CommandException(CommandEnum.PLOT, "cannot have more than one point");
    }

    @Override
    public Color getFillColor(){
        throw new CommandException(CommandEnum.LINE, "cannot have a fill color");
    }

    @Override
    public void setFillColor(Color fillColor) {
        throw new CommandException(CommandEnum.PLOT, "cannot have a fill color");
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
