package Tests;

import Commands.CommandException;
import Enums.CommandType;
import Commands.Plot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

class PlotTests {
    private Plot plot;
    private int size = 1000;

    @BeforeEach
    void beforeEach(){
        plot = null;
        plot = new Plot(1,1, Color.BLACK, size);
    }

    @Test
    void testCompleteConstructor(){
        plot = new Plot(0.5, 0.5, Color.BLACK, size);
        assertEquals(CommandType.PLOT, plot.getCommandType());
    }

    @Test
    void testSetX(){
        assertEquals(1, plot.getStartX());
    }

    @Test
    void testSetY(){
        assertEquals(1, plot.getStartY());
    }

    @Test
    void addXPointException() {
        assertThrows(CommandException.class, () -> plot.addXPoint(1), "PLOT cannot add more than one point");
    }

    @Test
    void addYPointException() {
        assertThrows(CommandException.class, () -> plot.addYPoint(1), "PLOT cannot add more than one point");
    }

    @Test
    void getXPointException() {
        assertThrows(CommandException.class, () -> plot.getXPoint(), "PLOT cannot have more than one point");
    }

    @Test
    void getYPointException() {
        assertThrows(CommandException.class, () -> plot.getYPoint(), "PLOT cannot have more than one point");
    }

    @Test
    void testIsCommandFinished() {
        assertEquals(true, plot.isCommandFinished());
    }

}
