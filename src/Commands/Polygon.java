package Commands;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Polygon extends Command {
    private int startX, startY;
    private ArrayList<Integer> xPoints = new ArrayList<>();
    private ArrayList<Integer> yPoints = new ArrayList<>();
    private boolean commandFinished;

    public Polygon(int startX, int startY, Color penColor, Color fillColor){
        super(penColor, fillColor, CommandType.POLYGON);
        this.startX = startX;
        this.startY = startY;
        xPoints.add(startX);
        yPoints.add(startY);
        commandFinished = false;
    }

    @Override
    public void addXPoint(int x) {
        if(commandFinished) throw new CommandException(CommandType.POLYGON, "cannot add more x points after command finished");
        xPoints.add(x);
    }

    @Override
    public void addYPoint(int y) {
        if(commandFinished) throw new CommandException(CommandType.POLYGON, "cannot add more y points after the command has finished");
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
        //returns the last x point added to the polygon
        return xPoints.get(xPoints.size()-1);
    }

    @Override
    public int getYPoint() {
        //returns the last y point added to the polygon
        return yPoints.get(yPoints.size()-1);
    }

    @Override
    public void draw(Graphics graphics) {
        int[] x_points = arrayListToIntArray(xPoints);
        int[] y_points = arrayListToIntArray(yPoints);
        if(x_points.length != y_points.length) {
            throw new CommandException(CommandType.POLYGON, "has unequal numbers of x and y points");
        }

        if(!commandFinished){
            drawPolyline(graphics, x_points, y_points);
        }else{
            drawPolygon(graphics, x_points, y_points);
        }
    }

    /**
     * draws the uncompleted polygon's polyline
     * @param graphics the graphics component to the draw the polyline on
     * @param x_points the x points of the polyline
     * @param y_points the y points of the polyline
     */
    private void drawPolyline(Graphics graphics, int[] x_points, int[] y_points){
        graphics.setColor(getPenColor());
        graphics.drawPolyline(x_points, y_points, x_points.length);
    }

    /**
     * draws the completed polygon
     * @param graphics the graphics component to draw the polyline on
     * @param x_points the x points of the polygon
     * @param y_points the y points of the polygon
     */
    private void drawPolygon(Graphics graphics, int[] x_points, int[] y_points){
        if(getFillColor() != null){
            graphics.setColor(getFillColor());
            graphics.fillPolygon(x_points, y_points, x_points.length);
        }
        graphics.setColor(getPenColor());
        graphics.drawPolygon(x_points, y_points, x_points.length);
    }

    /**
     * helper method to convert an ArrayList of integer to an array of primitive ints
     * @param arrayList the ArrayList to convert
     * @return an int array of the elements of the supplied ArrayList
     */
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
