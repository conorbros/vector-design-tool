package Commands;
import VecFile.VecFileException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Polygon extends Command {
    private int startX, startY;
    private ArrayList<Integer> xPoints;
    private ArrayList<Integer> yPoints;
    private boolean commandFinished;

    public Polygon(int startX, int startY, Color penColor, Color fillColor){
        super(penColor, fillColor, CommandType.POLYGON);
        this.startX = startX;
        this.startY = startY;
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        xPoints.add(startX);
        yPoints.add(startY);
        commandFinished = false;
    }

    public Polygon(ArrayList<Integer> xPoints, ArrayList<Integer> yPoints, Color penColor, Color fillColor){
        super(penColor, fillColor, CommandType.POLYGON);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        commandFinished = true;
    }

    @Override
    public String toVEC() {
        int pointCount = xPoints.size();

        StringBuilder result = new StringBuilder("POLYGON ");
        for(int i = 0; i < pointCount; i++){
            result.append(IntToDecimalConvert(xPoints.get(i))).append(" ").append(IntToDecimalConvert(yPoints.get(i)));
        }
        return result.toString();
    }

    @Override
    public String toString(){
        ArrayList<Integer> xpos = xPoints;
        ArrayList<Integer> ypos = yPoints;

        return "POLYGON" + " start x,y: " +
                IntToDecimalConvert(xpos.get(0)) + ", " + IntToDecimalConvert(ypos.get(0)) +
                " end x,y: " + IntToDecimalConvert(xpos.get(xpos.size()-1)) + ", " + IntToDecimalConvert(ypos.get(ypos.size()-1));
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

    /**
     * Returns an array of all the xPoints of the polygon
     * @return
     */
    public ArrayList<Integer> getxPoints() {
        return xPoints;
    }

    /**
     * Returns an array of all the yPoints of the polygon
     * @return
     */
    public ArrayList<Integer> getyPoints() {
        return yPoints;
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
