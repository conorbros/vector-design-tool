package Commands;

import java.awt.*;

public class Rectangle extends Command {
    private Integer x1, y1, x2, y2;
    private boolean commandFinished;

    public Rectangle(int x1, int y1, Color penColor, Color fillColor) {
        super(penColor, fillColor, CommandEnum.RECTANGLE);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = null;
        this.y2 = null;
        commandFinished = false;
    }

    @Override
    public void addXPoint(int x) {
        if(commandFinished) throw new CommandException(CommandEnum.RECTANGLE, "cannot change coordinates after command finished");
        this.x2 = x;
    }

    @Override
    public void addYPoint(int y) {
        if(commandFinished) throw new CommandException(CommandEnum.RECTANGLE, "cannot change coordinates after command finished");
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
            throw new CommandException(CommandEnum.RECTANGLE, "cannot be set finished until all coordinates supplied.");
        }
    }
}
