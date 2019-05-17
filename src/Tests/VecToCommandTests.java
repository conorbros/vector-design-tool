package Tests;

import CommandList.CommandList;
import Commands.Polygon;
import Commands.Rectangle;
import Commands.*;
import VecFile.VecFileException;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static VecFile.VecToCommand.ConvertVecStrToCommandList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VecToCommandTests {

    @Test
    public void testPlot() throws VecFileException {
        String VecString = "PLOT 0.0 0.0\r\n";
        CommandList plot = ConvertVecStrToCommandList(VecString);
        Command res = plot.getLastCommand();
        Command exp = new Plot(0, 0, Color.BLACK);

        boolean testResult = true;
        if(res.getCommandType() != exp.getCommandType()) testResult = false;
        if(res.getPenColor() != exp.getPenColor()) testResult = false;
        if(res.getStartX() != exp.getStartX()) testResult = false;
        if(res.getStartY() != exp.getStartY()) testResult = false;

        assertTrue(testResult);
    }

    @Test
    public void testLine() throws VecFileException {
        String VecString = "LINE 1.0 1.0 0.0 0.0\r\n";
        CommandList line = ConvertVecStrToCommandList(VecString);
        Command res = line.getLastCommand();
        Command exp = new Line(1000, 1000, 0, 0, Color.BLACK);

        boolean testResult = true;
        if(res.getCommandType() != exp.getCommandType()) testResult = false;
        if(res.getPenColor() != exp.getPenColor()) testResult = false;
        if(res.getStartX() != exp.getStartX()) testResult = false;
        if(res.getStartY() != exp.getStartY()) testResult = false;
        if(res.getXPoint() != exp.getXPoint()) testResult = false;
        if(res.getYPoint() != exp.getYPoint()) testResult = false;

        assertTrue(testResult);
    }

    @Test
    public void testRectangle() throws VecFileException {
        String VecString = "RECTANGLE 0.5 0.5 1.0 1.0\r\n";
        CommandList rec = ConvertVecStrToCommandList(VecString);
        Command res = rec.getLastCommand();
        Command exp = new Rectangle(500, 500, 1000, 1000, Color.BLACK, null);

        boolean testResult = true;
        if(res.getCommandType() != exp.getCommandType()) testResult = false;
        if(res.getPenColor() != exp.getPenColor()) testResult = false;
        if(res.getStartX() != exp.getStartX()) testResult = false;
        if(res.getStartY() != exp.getStartY()) testResult = false;
        if(res.getXPoint() != exp.getXPoint()) testResult = false;
        if(res.getYPoint() != exp.getYPoint()) testResult = false;
        if(res.getFillColor() != exp.getFillColor()) testResult = false;

        assertTrue(testResult);
    }

    @Test
    public void testEllipse() throws VecFileException {
        String VecString = "ELLIPSE 0.0 0.0 0.5 0.5\r\n";
        CommandList ell = ConvertVecStrToCommandList(VecString);
        Command res = ell.getLastCommand();
        Command exp = new Ellipse(0, 0, 500, 500, Color.BLACK, null);

        boolean testResult = true;
        if(res.getCommandType() != exp.getCommandType()) testResult = false;
        if(res.getPenColor() != exp.getPenColor()) testResult = false;
        if(res.getStartX() != exp.getStartX()) testResult = false;
        if(res.getStartY() != exp.getStartY()) testResult = false;
        if(res.getXPoint() != exp.getXPoint()) testResult = false;
        if(res.getYPoint() != exp.getYPoint()) testResult = false;
        if(res.getFillColor() != exp.getFillColor()) testResult = false;

        assertTrue(testResult);
    }

    @Test
    public void testPolygon() throws VecFileException {
        String VecString = "POLYGON 0.0 0.0 1.0 1.0 0.5 0.5 0.0 0.5\r\n";
        CommandList poly = ConvertVecStrToCommandList(VecString);
        Command res = poly.getLastCommand();

        Integer[] xpos = {0, 1000, 500, 0};
        Integer[] ypos = {0, 1000, 500, 500};
        ArrayList<Integer> xPoints = new ArrayList<>();
        ArrayList<Integer> yPoints = new ArrayList<>();
        xPoints.addAll(Arrays.asList(xpos));
        yPoints.addAll(Arrays.asList(ypos));

        Polygon exp = new Polygon(xPoints, yPoints, Color.BLACK, null);

        boolean testResult = true;
        if(res.getCommandType() != exp.getCommandType()) testResult = false;
        if(!((Polygon)res).getxPoints().equals(exp.getxPoints())) testResult = false;
        if(!((Polygon)res).getyPoints().equals(exp.getyPoints())) testResult = false;
        if(res.getFillColor() != exp.getFillColor()) testResult = false;
        if(res.getPenColor() != exp.getPenColor()) testResult = false;

        assertTrue(testResult);
    }

    @Test
    public void testPen() throws VecFileException {
        String VecString = "PEN #ff0000\r\n";
        VecString += "PLOT 1.0 1.0\r\n";

        CommandList pen = ConvertVecStrToCommandList(VecString);
        Command res = pen.getLastCommand();
        assertTrue(res.getPenColor().equals(Color.RED));
    }

    @Test
    public void testFill() throws VecFileException {
        String VecString = "FILL #ff0000\r\n";
        VecString += "RECTANGLE 0.0 0.0 1.0 1.0\r\n";

        CommandList pen = ConvertVecStrToCommandList(VecString);
        Command res = pen.getLastCommand();
        assertTrue(res.getFillColor().equals(Color.RED));
    }
}