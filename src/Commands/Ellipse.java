package Commands;

import java.awt.*;

public class Ellipse extends Command {
    private Double x1, y1, x2, y2;
    private boolean commandFinished;

    public Ellipse(int x1, int y1, Color penColor, Color fillColor, int screenSize) {
        super(penColor, fillColor, CommandType.ELLIPSE, screenSize);
        this.x1 = IntToDouble(x1);
        this.y1 = IntToDouble(y1);
        this.x2 = null;
        this.y2 = null;
        commandFinished = false;
    }
    //TODO: change this constructor, and other shape constructors
    public Ellipse(double x1, double y1, double x2, double y2, Color penColor, Color fillColor, int screenSize){
        super(penColor, fillColor, CommandType.ELLIPSE, screenSize);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        commandFinished = true;
    }

    @Override
    public String toVEC() {
        return "ELLIPSE " + x1 + " " + y1 + " " +  x2 + " " + y2;
    }

    @Override
    public void addXPoint(int x) {
        if(commandFinished) throw new CommandException(CommandType.ELLIPSE, "cannot change coordinates after command finished");
        this.x2 = IntToDouble(x);
    }

    @Override
    public void addYPoint(int y) {
        if(commandFinished) throw new CommandException(CommandType.ELLIPSE, "cannot change coordinates after command finished");
        this.y2 = IntToDouble(y);
    }

    @Override
    public int getStartX() {
        return DoubleToInt(x1);
    }

    @Override
    public int getStartY() {
        return DoubleToInt(y1);
    }

    @Override
    public int getXPoint() {
        return DoubleToInt(x2);
    }

    @Override
    public int getYPoint() {
        return DoubleToInt(y2);
    }

    @Override
    public void draw(Graphics graphics, int screenSize) {
        setScreenSize(screenSize);

        if(y2 == null || x2 == null) return;
        if(getFillColor() != null){
            graphics.setColor(getFillColor());
            graphics.fillOval(getStartX(), getStartY(), getXPoint()-getStartX(), getYPoint()-getStartY());
        }
        graphics.setColor(getPenColor());
        graphics.drawOval(getStartX(), getStartY(), getXPoint()-getStartX(), getYPoint()-getStartY());
    }

    @Override
    public boolean isCommandFinished() {
        return commandFinished;
    }

    @Override
    public void setCommandFinished() {
        if(x2 != null && y2 != null) {
            commandFinished = true;
        } else {
            throw new CommandException(CommandType.ELLIPSE, "cannot be set finished until all coordinates supplied.");
        }
    }
}
