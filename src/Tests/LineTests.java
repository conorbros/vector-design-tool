package Tests;

import java.awt.*;

import Commands.CommandException;
import Commands.Line;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTests {
    private Line line;
    private int size = 1000;

    @BeforeEach
    void beforeEach(){
        line = null;
        line = new Line(0, 0, Color.BLACK, size);
    }

    @Test
    void testCompleteConstructor(){
        line = new Line(0,0,1.0,1.0, Color.BLACK, size);
        assertEquals(0, line.getStartX());
    }

    @Test
    void testSetX(){
        line.addXPoint(1);
        assertEquals(0, line.getStartX());
        assertEquals(1, line.getXPoint());
    }

    @Test
    void addXPointException(){
        line.addXPoint(1);
        line.addYPoint(1);
        line.setCommandFinished();
        assertThrows(CommandException.class, () -> line.addXPoint(1), "LINE cannot change coordinates after command finished");
    }

    @Test
    void testSetY(){
        line.addYPoint(4);
        assertEquals(0, line.getStartY());
        assertEquals(4, line.getYPoint());
    }

    @Test
    void addYPointException(){
        line.addXPoint(1);
        line.addYPoint(1);
        line.setCommandFinished();
        assertThrows(CommandException.class, () -> line.addYPoint(1), "LINE cannot change coordinates after command finished");
    }

    @Test
    void testCommandFinished() {
        assertFalse(line.isCommandFinished());
        line.addXPoint(1);
        line.addYPoint(1);
        line.setCommandFinished();
        assertTrue(line.isCommandFinished());
    }

    @Test
    void testSetCommandFinishedException() {
        assertThrows(CommandException.class, () -> line.setCommandFinished(), "LINE cannot be set finished until all coordinates supplied.");
    }

}
