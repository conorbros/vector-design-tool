package Tests;

import Commands.CommandException;
import Commands.Plot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class PlotTests {
    private Plot plot;

    @BeforeEach
    public void beforeEach(){
        plot = null;
        plot = new Plot(1,1, Color.BLACK);
    }

    @Test
    public void testSetX(){
        assertEquals(1, plot.getStartX());
    }

    @Test
    public void testSetY(){
        assertEquals(1, plot.getStartY());
    }

    @Test
    public void addXPointException() {
        assertThrows(CommandException.class, () -> plot.addXPoint(1), "PLOT cannot add more than one point");
    }

    @Test
    public void addYPointException() {
        assertThrows(CommandException.class, () -> plot.addYPoint(1), "PLOT cannot add more than one point");
    }

    @Test
    public void getXPointException() {
        assertThrows(CommandException.class, () -> plot.getXPoint(), "PLOT cannot have more than one point");
    }

    @Test
    public void getYPointException() {
        assertThrows(CommandException.class, () -> plot.getYPoint(), "PLOT cannot have more than one point");
    }

    @Test
    public void testSetPenColor() {
        plot.setPenColor(Color.WHITE);
        assertEquals(Color.WHITE, plot.getPenColor());
    }

    @Test
    public void testFillColor() {
        assertThrows(CommandException.class, () -> plot.setFillColor(Color.WHITE), "PLOT cannot have a fill color");
        assertThrows(CommandException.class, () -> plot.getFillColor(), "PLOT cannot have a fill color");
    }

    @Test
    public void testIsCommandFinished() {
        assertEquals(true, plot.isCommandFinished());
    }

}
