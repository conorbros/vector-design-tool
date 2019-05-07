package Tests;

import java.awt.*;
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
    public void testSetY(){
        line.addStartYPoint(3);
        line.addYPoint(4);
        assertEquals(3, line.getStartY());
        assertEquals(4, line.getYPoint());
    }

    @Test
    public void testSetPenColor(){
        line.setPenColor(Color.WHITE);
        assertEquals(Color.WHITE, line.getPenColor());
    }

    @Test
    public void testSetFillColor(){
        line.setFillColor(Color.WHITE);
        assertEquals(Color.WHITE, line.getFillColor());
    }

}
