package Tests;

import java.awt.*;

import Commands.CommandException;
import Commands.Line;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineTests {
    private Line line;

    @BeforeEach
    public void beforeEach(){
        line = null;
        line = new Line(0, 0, Color.BLACK);
    }

    @Test
    public void testSetX(){
        line.addStartXPoint(1);
        line.addXPoint(1);
        assertEquals(1, line.getStartX());
        assertEquals(1, line.getXPoint());
    }

    @Test
    public void addXPointException(){
        line.addXPoint(1);
        line.addYPoint(1);
        line.setCommandFinished();
        assertThrows(CommandException.class, () -> line.addXPoint(1), "LINE cannot change coordinates after command finished");
    }

    @Test
    public void testSetY(){
        line.addStartYPoint(3);
        line.addYPoint(4);
        assertEquals(3, line.getStartY());
        assertEquals(4, line.getYPoint());
    }

    @Test
    public void addYPointException(){
        line.addXPoint(1);
        line.addYPoint(1);
        line.setCommandFinished();
        assertThrows(CommandException.class, () -> line.addYPoint(1), "LINE cannot change coordinates after command finished");
    }

    @Test
    public void testSetPenColor(){
        line.setPenColor(Color.WHITE);
        assertEquals(Color.WHITE, line.getPenColor());
    }

    @Test
    public void testFillColor() {
        assertThrows(CommandException.class, () -> line.setFillColor(Color.WHITE), "LINE cannot have a fill color");
    }

    @Test
    public void testCommandFinished() {
        assertEquals(false, line.isCommandFinished());
        line.addXPoint(1);
        line.addYPoint(1);
        line.setCommandFinished();
        assertEquals(true, line.isCommandFinished());
    }

    @Test
    public void testSetCommandFinishedException() {
        assertThrows(CommandException.class, () -> line.setCommandFinished(), "LINE cannot be set finished until all coordinates supplied.");
    }

}
