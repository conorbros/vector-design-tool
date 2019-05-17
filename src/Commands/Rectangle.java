package Commands;

import java.awt.*;

public class Rectangle extends Command {
    private Integer x1, y1, x2, y2;
    private boolean commandFinished;

    public Rectangle(int x1, int y1, Color penColor, Color fillColor) {
        super(penColor, fillColor, CommandType.RECTANGLE);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = null;
        this.y2 = null;
        commandFinished = false;
    }

    public Rectangle(int x1, int y1, int x2, int y2, Color penColor, Color fillColor){
        super(penColor, fillColor, CommandType.RECTANGLE);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        commandFinished = true;
    }

    @Override
    public String toString() {
        return "RECTANGLE " + IntToDecimalConvert(x1) + " " + IntToDecimalConvert(y1) + " " + IntToDecimalConvert(x2) + " " + IntToDecimalConvert(y2);
    }

    @Override
    public void addXPoint(int x) {
        if(commandFinished) throw new CommandException(CommandType.RECTANGLE, "cannot change coordinates after command finished");
        this.x2 = x;
    }

    @Override
    public void addYPoint(int y) {
        if(commandFinished) throw new CommandException(CommandType.RECTANGLE, "cannot change coordinates after command finished");
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
        if(y2 == null || x2 == null) return;
        if(getFillColor() != null){
            graphics.setColor(getFillColor());
            graphics.fillRect(x1, y1, x2-x1, y2-y1);
        }
        graphics.setColor(getPenColor());
        graphics.drawRect(x1, y1, x2-x1, y2-y1);
    }

    @Override
    public boolean isCommandFinished() {
        return commandFinished;
    }

    @Override
    public void setCommandFinished() {
        if(x2 != null && y2 != null){
            commandFinished = true;
        }else{
            throw new CommandException(CommandType.RECTANGLE, "cannot be set finished until all coordinates supplied.");
        }
    }
}
