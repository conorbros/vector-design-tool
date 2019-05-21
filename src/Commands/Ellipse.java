package Commands;

import java.awt.*;

public class Ellipse extends Command {
    private Double x1, y1, x2, y2;
    private boolean commandFinished;

    /**
     * Constructs a new Ellipse object, created from the VECPanel, with graphics coordinates
     * @param x1 The start x coordinate drawn
     * @param y1 The start y coordinate drawn
     * @param penColor The Pen Color of the Ellipse
     * @param fillColor The Fill Color of the Ellipse
     * @param screenSize The current screenSize of the VECPanel
     */
    public Ellipse(int x1, int y1, Color penColor, Color fillColor, int screenSize) {
        super(penColor, fillColor, CommandType.ELLIPSE, screenSize);
        this.x1 = IntToDouble(x1);
        this.y1 = IntToDouble(y1);
        this.x2 = null;
        this.y2 = null;
        commandFinished = false;
    }

    /**
     * Constructs a new Ellipse Object, imported from an existing VEC file
     * @param x1 The VEC start x coordinate
     * @param y1 The VEC start y coordinate
     * @param x2 The VEC end x coordinate
     * @param y2 The VEC end y coordinate
     * @param penColor The Pen Color of the Ellipse object
     * @param fillColor The Fill Color of the Ellipse object
     * @param screenSize The screenSize of the VEC Panel it will be drawn on
     */
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
