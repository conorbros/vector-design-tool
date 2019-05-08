package Tests;
import java.awt.*;
import Commands.Polygon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Commands.CommandEnum;
import Commands.CommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PolygonTests {
    private Polygon poly;

    @BeforeEach
    public void beforeEach(){
        poly = null;
        poly = new Polygon(1, 1, Color.BLACK, Color.BLACK);
    }

    @Test
    public void testPenColor(){
        assertEquals(Color.BLACK, poly.getPenColor());
    }

    @Test void testFillColor(){
        assertEquals(Color.BLACK, poly.getFillColor());
    }

    @Test
    public void getCommandType(){
        assertEquals(CommandEnum.POLYGON, poly.getCommandType());
    }

    @Test
    public void isCommandFinished(){
        poly.addXPoint(2);
        poly.addYPoint(2);
        assertFalse(poly.isCommandFinished());

        poly.addXPoint(0);
        poly.addXPoint(0);
        poly.addXPoint(1);
        poly.addYPoint(1);
        assertTrue(poly.isCommandFinished());
    }

    @Test
    public void setCommandFinished(){
        poly.addXPoint(1);
        poly.addYPoint(5);

        poly.setCommandFinished();

        assertEquals(1, poly.getXPoint());
        assertEquals(1, poly.getYPoint());
        assertTrue(poly.isCommandFinished());
    }
}
