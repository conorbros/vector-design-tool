package Commands;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.XMLFormatter;

public class Polygon extends Command {
    private int startX, startY;
    private ArrayList<Integer> xPoints = new ArrayList<>();
    private ArrayList<Integer> yPoints = new ArrayList<>();
    private int endX, endY;
    private boolean commandFinished;

    public Polygon(int startX, int startY, Color penColor, Color fillColor){
        super(penColor, fillColor, CommandEnum.POLYGON);
        this.startX = startX;
        this.startY = startY;
        xPoints.add(startX);
        yPoints.add(startY);
        commandFinished = false;
    }

    @Override
    public void addStartXPoint(int x) {
        this.startX = x;
    }

    @Override
    public void addStartYPoint(int y) {
        this.startY = y;
    }

    @Override
    public void addXPoint(int x) {
        xPoints.add(x);
    }

    @Override
    public void addYPoint(int y) {
        if (xPoints.get(xPoints.size()-1) == startX && y == startY) commandFinished = true;
        yPoints.add(y);
    }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    @Override
    public int getXPoint() {
        return xPoints.get(xPoints.size()-1);
    }

    @Override
    public int getYPoint() {
        return yPoints.get(yPoints.size()-1);
    }

    @Override
    public void draw(Graphics graphics) {
        int[] x_points = arrayListToIntArray(xPoints);
        int[] y_points = arrayListToIntArray(yPoints);
        if(x_points.length != y_points.length) throw new CommandException(CommandEnum.POLYGON, "has unequal numbers of x and y points");
        if(!commandFinished){
            drawPolyline(graphics, x_points, y_points);
        }else{
            drawPolygon(graphics, x_points, y_points);
        }
    }

    private void drawPolyline(Graphics graphics, int[] x_points, int[] y_points){
        graphics.setColor(getPenColor());
        graphics.drawPolyline(x_points, y_points, x_points.length);
    }

    private void drawPolygon(Graphics graphics, int[] x_points, int[] y_points){
        graphics.setColor(getFillColor());
        graphics.drawPolyline(x_points, y_points, x_points.length);
        graphics.setColor(getPenColor());
        graphics.drawPolygon(x_points, y_points, x_points.length);
    }

    private int[] arrayListToIntArray(ArrayList<Integer> arrayList){
        int[] array = new int[arrayList.size()];
        Iterator<Integer> iterator = arrayList.iterator();

        for (int i = 0; i < array.length; i++){
            array[i] = iterator.next();
        }
        return array;
    }

    @Override
    public boolean isCommandFinished() {
        return commandFinished;
    }

    @Override
    public void setCommandFinished() {
        addXPoint(startX);
        addYPoint(startY);
        commandFinished = true;
    }
}
