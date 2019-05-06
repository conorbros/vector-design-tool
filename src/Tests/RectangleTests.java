package Tests;

import java.awt.*;
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
    public void testSetX(){
        rec.setX1(1);
        rec.setX2(1);
        assertEquals(1, rec.getX1());
        assertEquals(1, rec.getX2());
    }

    @Test
    public void testSetY(){
        rec.setY1(3);
        rec.setY2(4);
        assertEquals(3, rec.getY1());
        assertEquals(4, rec.getY2());
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

}