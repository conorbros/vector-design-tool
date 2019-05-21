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

class VecToCommandTests {
    private int size = 1000;

    @Test
    void testPlot() throws VecFileException {
        String VecString = "PLOT 0.0 0.0\r\n";
        CommandList plot = ConvertVecStrToCommandList(VecString, size);
        Command res = plot.getLastCommand();
        Command exp = new Plot(0.0, 0.0, Color.BLACK, size);

        boolean testResult = true;
        if(res.getCommandType() != exp.getCommandType()) testResult = false;
        if(res.getPenColor() != exp.getPenColor()) testResult = false;
        if(res.getStartX() != exp.getStartX()) testResult = false;
        if(res.getStartY() != exp.getStartY()) testResult = false;

        assertTrue(testResult);
    }

    @Test
    void testLine() throws VecFileException {
        String VecString = "LINE 1.0 1.0 0.0 0.0\r\n";
        CommandList line = ConvertVecStrToCommandList(VecString, size);
        Command res = line.getLastCommand();
        Command exp = new Line(1.0, 1.0, 0.0, 0.0, Color.BLACK, size);

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
    void testRectangle() throws VecFileException {
        String VecString = "RECTANGLE 0.5 0.5 1.0 1.0\r\n";
        CommandList rec = ConvertVecStrToCommandList(VecString, size);
        Command res = rec.getLastCommand();
        Command exp = new Rectangle(0.5, 0.5, 1.0, 1.0, Color.BLACK, null, size);

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
    void testEllipse() throws VecFileException {
        String VecString = "ELLIPSE 0.0 0.0 0.5 0.5\r\n";
        CommandList ell = ConvertVecStrToCommandList(VecString, size);
        Command res = ell.getLastCommand();
        Command exp = new Ellipse(0.0, 0.0, 0.5, 0.5, Color.BLACK, null, size);

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
    void testPolygon() throws VecFileException {
        String VecString = "POLYGON 0.0 0.0 1.0 1.0 0.5 0.5 0.0 0.5\r\n";
        CommandList poly = ConvertVecStrToCommandList(VecString, size);
        Command res = poly.getLastCommand();

        Double[] xpos = {0.0, 1.0, 0.5, 0.0};
        Double[] ypos = {0.0, 1.0, 0.5, 0.5};
        ArrayList<Double> xPoints = new ArrayList<>(Arrays.asList(xpos));
        ArrayList<Double> yPoints = new ArrayList<>(Arrays.asList(ypos));

        Polygon exp = new Polygon(xPoints, yPoints, Color.BLACK, null, size);

        boolean testResult = true;
        if(res.getCommandType() != exp.getCommandType()) testResult = false;
        if(res.getStartX() != exp.getStartX()) testResult = false;
        if(res.getStartY() != exp.getStartY()) testResult = false;
        if(res.getFillColor() != exp.getFillColor()) testResult = false;
        if(res.getPenColor() != exp.getPenColor()) testResult = false;

        assertTrue(testResult);
    }

    @Test
    void testPen() throws VecFileException {
        String VecString = "PEN #ff0000\r\n";
        VecString += "PLOT 1.0 1.0\r\n";

        CommandList pen = ConvertVecStrToCommandList(VecString, size);
        Command res = pen.getLastCommand();
        assertTrue(res.getPenColor().equals(Color.RED));
    }

    @Test
    void testFill() throws VecFileException {
        String VecString = "FILL #ff0000\r\n";
        VecString += "RECTANGLE 0.0 0.0 1.0 1.0\r\n";

        CommandList pen = ConvertVecStrToCommandList(VecString, size);
        Command res = pen.getLastCommand();
        assertTrue(res.getFillColor().equals(Color.RED));
    }
}