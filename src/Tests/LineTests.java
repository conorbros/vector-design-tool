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
        line = new Line(0, 0, Color.BLACK, Color.WHITE);
    }


    @Test
    public void testSetX(){
        line.setX1(1);
        line.setX2(1);
        assertEquals(1, line.getX1());
        assertEquals(1, line.getX2());
    }

    @Test
    public void testSetY(){
        line.setY1(3);
        line.setY2(4);
        assertEquals(3, line.getY1());
        assertEquals(4, line.getY2());
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
