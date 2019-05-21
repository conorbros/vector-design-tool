package Tests;

import java.awt.*;

import Commands.CommandType;
import Commands.CommandException;
import Commands.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTests {
    private Rectangle rec;
    private int size = 1000;

    @BeforeEach
    void beforeEach(){
        rec = null;
        rec = new Rectangle(0, 0, Color.BLACK, Color.WHITE, size);
    }

    @Test
    void testCompletedConstructor(){
        rec = new Rectangle(0.0, 0.0, 1.0, 1.0, Color.BLACK, null, size);
        assertTrue(rec.isCommandFinished());
    }

    @Test
    void addXPoint(){
        rec.addXPoint(1);
        assertEquals(0, rec.getStartX());
        assertEquals(1, rec.getXPoint());
    }

    @Test
    void addXPointException(){
        rec.addXPoint(1);
        rec.addYPoint(1);
        rec.setCommandFinished();
        assertThrows(CommandException.class, () -> rec.addXPoint(1), "RECTANGLE cannot change coordinates after command finished.");
    }

    @Test
    void addYPoint(){
        rec.addYPoint(4);
        assertEquals(0, rec.getStartY());
        assertEquals(4, rec.getYPoint());
    }

    @Test
    void addYPointException(){
        rec.addXPoint(1);
        rec.addYPoint(1);
        rec.setCommandFinished();
        assertThrows(CommandException.class, () -> rec.addYPoint(1), "RECTANGLE cannot change coordinates after command finished.");
    }

    @Test
    void testGetCommandType(){
        assertEquals(CommandType.RECTANGLE, rec.getCommandType());
    }

    @Test
    void testCommandFinished(){
        assertEquals(false, rec.isCommandFinished());
        rec.addYPoint(1);
        rec.addXPoint(1);
        rec.setCommandFinished();
        assertEquals(true, rec.isCommandFinished());
    }

    @Test
    void testCommandFinishedException(){
        assertThrows(CommandException.class, () -> rec.setCommandFinished(), "RECTANGLE command cannot be set finished until all coordinates supplied.");
    }
}