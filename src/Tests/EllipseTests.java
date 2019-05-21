package Tests;

import Commands.CommandType;
import Commands.CommandException;
import Commands.Ellipse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EllipseTests {
    private Ellipse ell;
    private int size = 1000;

    @BeforeEach
    void beforeEach(){
        ell= null;
        ell= new Ellipse(0, 0, Color.BLACK, Color.WHITE, size);
    }

    @Test
    void testCompleteConstructor(){
        ell = new Ellipse(0.0, 0.0, 1.0, 1.0, Color.BLACK, null, size);
        assertEquals(1000, ell.getStartX());
    }

    @Test
    void testToVEC(){
        ell = new Ellipse(0.0, 0.0, 1.0, 1.0, Color.BLACK, null, size);
        String expected = "ELLIPSE 0.0 0.0 1.0 1.0";
        assertEquals(expected, ell.toVEC());
    }


    @Test
    void addXPoint(){
        ell.addXPoint(1);
        assertEquals(0, ell.getStartX());
        assertEquals(1, ell.getXPoint());
    }

    @Test
    void addXPointException(){
        ell.addXPoint(1);
        ell.addYPoint(1);
        ell.setCommandFinished();
        assertThrows(CommandException.class, () -> ell.addXPoint(1), "ELLIPSE cannot change coordinates after command finished.");
    }

    @Test
    void addYPoint(){
        ell.addYPoint(4);
        assertEquals(0, ell.getStartY());
        assertEquals(4, ell.getYPoint());
    }

    @Test
    void addYPointException(){
        ell.addXPoint(1);
        ell.addYPoint(1);
        ell.setCommandFinished();
        assertThrows(CommandException.class, () -> ell.addYPoint(1), "ELLIPSE cannot change coordinates after command finished.");
    }

    @Test
    void testGetCommandType(){
        assertEquals(CommandType.ELLIPSE, ell.getCommandType());
    }

    @Test
    void testCommandFinished(){
        assertFalse(ell.isCommandFinished());
        ell.addYPoint(1);
        ell.addXPoint(1);
        ell.setCommandFinished();
        assertTrue(ell.isCommandFinished());
    }

    @Test
    void testCommandFinishedException(){
        assertThrows(CommandException.class, () -> ell.setCommandFinished(), "ELLIPSE command cannot be set finished until all coordinates supplied.");
    }
}
