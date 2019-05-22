package Commands;

import Enums.CommandType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Polygon extends Command {
    private Double startX, startY;
    private ArrayList<Double> xPoints, yPoints;
    private boolean commandFinished;

    /**
     * Constructs a Polygon Object, drawn from the VECPanel
     * @param startX The start x coordinate
     * @param startY The start y coordinate
     * @param penColor The Pen Color of the Polygon
     * @param fillColor The Fill Color of the Polygon
     * @param screenSize The screenSize of the VECPanel
     */
    public Polygon(int startX, int startY, Color penColor, Color fillColor, int screenSize){
        super(penColor, fillColor, CommandType.POLYGON, screenSize);
        this.startX = IntToDouble(startX);
        this.startY = IntToDouble(startY);
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
        xPoints.add(this.startX);
        yPoints.add(this.startY);
        commandFinished = false;
    }

    /**
     * Constructs a Polygon object, imported from a VEC command
     * @param xPoints The x coordinates of the polygon
     * @param yPoints The y coordinates of the polygon
     * @param penColor The Pen Color of the Polygon
     * @param fillColor The Fill Color of the Polygon
     * @param screenSize The screenSize of the VECPanel that the polygon will be drawn on
     */
    public Polygon(ArrayList<Double> xPoints, ArrayList<Double> yPoints, Color penColor, Color fillColor, int screenSize){
        super(penColor, fillColor, CommandType.POLYGON, screenSize);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.startX = xPoints.get(0);
        this.startY = yPoints.get(0);
        commandFinished = true;
    }

    @Override
    public String toVEC() {
        StringBuilder result = new StringBuilder("POLYGON");

        for(int i = 0; i < xPoints.size(); i++){
            result.append(" ").append(xPoints.get(i)).append(" ").append(yPoints.get(i));
        }
        return result.toString();
    }

    @Override
    public String toString(){
        return "POLYGON" + " start x,y: " +
                xPoints.get(0) + ", " + yPoints.get(0) +
                " end x,y: " + xPoints.get(xPoints.size()-1) + ", " + yPoints.get(yPoints.size()-1);
    }

    @Override
    public void addXPoint(int x) {
        if(commandFinished) throw new CommandException(CommandType.POLYGON, "cannot add more x points after command finished");
        xPoints.add(IntToDouble(x));
    }

    @Override
    public void addYPoint(int y) {
        if(commandFinished) throw new CommandException(CommandType.POLYGON, "cannot add more y points after the command has finished");
        yPoints.add(IntToDouble(y));
    }

    @Override
    public int getStartX() {
        return DoubleToInt(startX);
    }

    @Override
    public int getStartY() {
        return DoubleToInt(startY);
    }

    @Override
    public int getXPoint() {
        //returns the last x point added to the polygon
        return DoubleToInt(xPoints.get(xPoints.size()-1));
    }

    @Override
    public int getYPoint() {
        //returns the last y point added to the polygon
        return DoubleToInt(yPoints.get(yPoints.size()-1));
    }

    @Override
    public void draw(Graphics graphics, int screenSize) {
        setScreenSize(screenSize);

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
     * helper method to convert an ArrayList of Double to an array of primitive ints
     * @param arrayList the ArrayList to convert
     * @return an int array of the elements of the supplied ArrayList
     */
    private int[] arrayListToIntArray(ArrayList<Double> arrayList){
        int[] array = new int[arrayList.size()];
        Iterator<Double> iterator = arrayList.iterator();

        for (int i = 0; i < array.length; i++){
            array[i] = DoubleToInt(iterator.next());
        }
        return array;
    }

    @Override
    public boolean isCommandFinished() {
        return commandFinished;
    }

    @Override
    public void setCommandFinished() {
        xPoints.add(startX);
        yPoints.add(startY);
        commandFinished = true;
    }
}
