package Commands;

import java.awt.*;

public class Plot extends Command {
    private Double x, y;

    public Plot(int x, int y, Color penColor, int screenSize) {
        super(penColor, null, CommandType.PLOT, screenSize);
        this.x = IntToDouble(x);
        this.y =  IntToDouble(y);
    }

    public Plot(double x, double y, Color penColor, int screenSize) {
        super(penColor, null, CommandType.PLOT, screenSize);
        this.x = x;
        this.y = y;
    }

    @Override
    public void addXPoint(int x) {
        throw new CommandException(CommandType.PLOT, "cannot add more than one point");
    }

    @Override
    public void addYPoint(int y) {
        throw new CommandException(CommandType.PLOT, "cannot add more than one point");
    }

    @Override
    public int getStartX() {
        return DoubleToInt(x);
    }

    @Override
    public int getStartY() {
        return DoubleToInt(y);
    }

    @Override
    public int getXPoint() {
        throw new CommandException(CommandType.PLOT, "cannot have more than one point");
    }

    @Override
    public int getYPoint() {
        throw new CommandException(CommandType.PLOT, "cannot have more than one point");
    }

    @Override
    public Color getFillColor(){
        throw new CommandException(CommandType.LINE, "cannot have a fill color");
    }

    @Override
    public void setFillColor(Color fillColor) {
        throw new CommandException(CommandType.PLOT, "cannot have a fill color");
    }

    @Override
    public String toVEC() {
        return "PLOT " + (IntToDouble(getStartX()) + " " + IntToDouble(getStartY()));
    }

    @Override
    public void draw(Graphics graphics, int screenSize) {
        setScreenSize(screenSize);

        graphics.setColor(getPenColor());
        graphics.drawLine(getStartX(), getStartY(), getStartX(), getStartY());
    }

    @Override
    public boolean isCommandFinished() {
        return true;
    }

    @Override
    public void setCommandFinished() {

    }
}
