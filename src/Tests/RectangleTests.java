package Tests;

import java.awt.*;

import Commands.CommandEnum;
import Commands.CommandException;
import Commands.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangleTests {
    private Rectangle rec;

    @BeforeEach
    public void beforeEach(){
        rec = null;
        rec = new Rectangle(0, 0, Color.BLACK, Color.WHITE);
    }

    @Test
    public void addXPoint(){
        rec.addStartXPoint(1);
        rec.addXPoint(1);
        assertEquals(1, rec.getStartX());
        assertEquals(1, rec.getXPoint());
    }

    @Test
    public void addXPointException(){
        rec.addXPoint(1);
        rec.addYPoint(1);
        rec.setCommandFinished();
        assertThrows(CommandException.class, () -> rec.addXPoint(1), "RECTANGLE cannot change coordinates after command finished.");
    }

    @Test
    public void addYPoint(){
        rec.addStartYPoint(3);
        rec.addYPoint(4);
        assertEquals(3, rec.getStartY());
        assertEquals(4, rec.getYPoint());
    }

    @Test
    public void addYPointException(){
        rec.addXPoint(1);
        rec.addYPoint(1);
        rec.setCommandFinished();
        assertThrows(CommandException.class, () -> rec.addYPoint(1), "RECTANGLE cannot change coordinates after command finished.");
    }

    @Test
    public void testSetPenColor(){
        rec.setPenColor(Color.WHITE);
        assertEquals(Color.WHITE, rec.getPenColor());
    }

    @Test
    public void testSetFillColor(){
        rec.setFillColor(Color.WHITE);
        assertEquals(Color.WHITE, rec.getFillColor());
    }

    @Test
    public void testGetCommandType(){
        assertEquals(CommandEnum.RECTANGLE, rec.getCommandType());
    }

    @Test
    public void testCommandFinished(){
        assertEquals(false, rec.isCommandFinished());
        rec.addYPoint(1);
        rec.addXPoint(1);
        rec.setCommandFinished();
        assertEquals(true, rec.isCommandFinished());
    }

    @Test
    public void testCommandFinishedException(){
        assertThrows(CommandException.class, () -> rec.setCommandFinished(), "RECTANGLE command cannot be set finished until all coordinates supplied.");
    }

}