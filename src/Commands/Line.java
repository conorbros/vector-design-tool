package Commands;

import Enums.CommandType;

import java.awt.*;

public class Line extends Command {
    private Double x1, y1, x2, y2;
    private boolean commandFinished;

    /**
     * Constructs a Line object, drawn on the VECPanel
     * @param x The start x coordinate of the Line
     * @param y The start y coordinate of the Line
     * @param penColor Color object of the Pen Color of the Line object
     * @param screenSize The screenSize of the VECPanel the Line is drawn on
     */
    public Line(int x, int y, Color penColor, int screenSize) {
        //Line has no fill color
        super(penColor, null, CommandType.LINE, screenSize);
        this.x1 = IntToDouble(x);
        this.y1 =  IntToDouble(y);
        this.x2 = null;
        this.y2 = null;
        commandFinished = false;
    }

    /**
     * Constructs a Line object from an imported VEC command
     * @param x1 The start x coordinate of the Line
     * @param y1 The start y coordinate of the Line
     * @param x2 The end x coordinate of the Line
     * @param y2 The end y coordinate of the Line
     * @param penColor Color object of the Pen Color of the Line
     * @param screenSize The screenSize of the VECPanel that the line is to be drawn on
     */
    public Line(double x1, double y1, double x2, double y2, Color penColor, int screenSize){
        //Line has no fill color
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
