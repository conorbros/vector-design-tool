package Tests;

import Commands.CommandType;
import Commands.CommandException;
import Commands.Ellipse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EllipseTests {

    private Ellipse ell;

    @BeforeEach
    public void beforeEach(){
        ell= null;
        ell= new Ellipse(0, 0, Color.BLACK, Color.WHITE);
    }

    @Test
    public void addXPoint(){
        ell.addXPoint(1);
        assertEquals(0, ell.getStartX());
        assertEquals(1, ell.getXPoint());
    }

    @Test
    public void addXPointException(){
        ell.addXPoint(1);
        ell.addYPoint(1);
        ell.setCommandFinished();
        assertThrows(CommandException.class, () -> ell.addXPoint(1), "ELLIPSE cannot change coordinates after command finished.");
    }

    @Test
    public void addYPoint(){
        ell.addYPoint(4);
        assertEquals(0, ell.getStartY());
        assertEquals(4, ell.getYPoint());
    }

    @Test
    public void addYPointException(){
        ell.addXPoint(1);
        ell.addYPoint(1);
        ell.setCommandFinished();
        assertThrows(CommandException.class, () -> ell.addYPoint(1), "ELLIPSE cannot change coordinates after command finished.");
    }

    @Test
    public void testSetPenColor(){
        ell.setPenColor(Color.WHITE);
        assertEquals(Color.WHITE, ell.getPenColor());
    }

    @Test
    public void testSetFillColor(){
        ell.setFillColor(Color.WHITE);
        assertEquals(Color.WHITE, ell.getFillColor());
    }

    @Test
    public void testGetCommandType(){
        assertEquals(CommandType.ELLIPSE, ell.getCommandType());
    }

    @Test
    public void testCommandFinished(){
        assertEquals(false, ell.isCommandFinished());
        ell.addYPoint(1);
        ell.addXPoint(1);
        ell.setCommandFinished();
        assertEquals(true, ell.isCommandFinished());
    }

    @Test
    public void testCommandFinishedException(){
        assertThrows(CommandException.class, () -> ell.setCommandFinished(), "ELLIPSE command cannot be set finished until all coordinates supplied.");
    }
}
