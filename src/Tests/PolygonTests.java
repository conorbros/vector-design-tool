package Tests;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import Commands.Polygon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Enums.CommandType;

import static org.junit.jupiter.api.Assertions.*;

class PolygonTests {
    private Polygon poly;
    private int size = 1000;

    @BeforeEach
    void beforeEach(){
        poly = null;
        poly = new Polygon(1, 1, Color.BLACK, Color.BLACK, size);
    }

    @Test
    void testCompletedConstructor(){
        Double[] xpos = {0.0, 1.0, 0.5, 0.0};
        Double[] ypos = {0.0, 1.0, 0.5, 0.0};
        ArrayList<Double> xPoints = new ArrayList<>(Arrays.asList(xpos));
        ArrayList<Double> yPoints = new ArrayList<>(Arrays.asList(ypos));

        poly = new Polygon(xPoints, yPoints, Color.BLACK, Color.WHITE, size);

        boolean testResult = true;
        if(poly.getCommandType() != CommandType.POLYGON) testResult = false;
        if(poly.getFillColor() != Color.WHITE) testResult = false;
        if(poly.getPenColor() != Color.BLACK) testResult = false;

        assertTrue(testResult);
    }

    @Test
    void testPenColor(){
        assertEquals(Color.BLACK, poly.getPenColor());
    }

    @Test void testFillColor(){
        assertEquals(Color.BLACK, poly.getFillColor());
    }

    @Test
    void getCommandType(){
        assertEquals(CommandType.POLYGON, poly.getCommandType());
    }

    @Test
    void setCommandFinished(){
        poly.addXPoint(1);
        poly.addYPoint(5);

        poly.setCommandFinished();

        assertEquals(1, poly.getXPoint());
        assertEquals(1, poly.getYPoint());
        assertTrue(poly.isCommandFinished());
    }
}
