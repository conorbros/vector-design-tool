package Commands;

import java.awt.*;

public class Line extends Command {
    private Double x1, y1, x2, y2;
    private boolean commandFinished;

    public Line(int x, int y, Color penColor, int screenSize) {
        super(penColor, null, CommandType.LINE, screenSize);
        this.x1 = IntToDouble(x);
        this.y1 =  IntToDouble(y);
        this.x2 = null;
        this.y2 = null;
        commandFinished = false;
    }

    public Line(double x1, double y1, double x2, double y2, Color penColor, int screenSize){
        super(penColor, null, CommandType.LINE, screenSize);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        commandFinished = true;
    }

    @Override
    public void addXPoint(int x) {
        if(commandFinished) throw new CommandException(CommandType.LINE, "cannot change coordinates after command finished");
        this.x2 = IntToDouble(x);
    }

    @Override
    public void addYPoint(int y) {
        if(commandFinished) throw new CommandException(CommandType.LINE, "cannot change coordinates after command finished");
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
    public Color getFillColor(){
        throw new CommandException(CommandType.LINE, "cannot have a fill color");
    }

    @Override
    public void setFillColor(Color fillColor) {
        throw new CommandException(CommandType.LINE, "cannot have a fill color");
    }

    @Override
    public String toVEC() {
        return "LINE " + (IntToDouble(getStartX()) + " " + IntToDouble(getStartY()) + " " + IntToDouble(getXPoint()) + " " + IntToDouble(getYPoint()));
    }

    @Override
    public void draw(Graphics graphics, int screenSize) {
        setScreenSize(screenSize);

        if(y2 == null || x2 == null) return;
        graphics.setColor(getPenColor());
        graphics.drawLine(getStartX(), getStartY(), getXPoint(), getYPoint());
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
            throw new CommandException(CommandType.LINE, "cannot be set finished until all coordinates supplied.");
        }
    }
}
