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
        rec.addStartXPoint(1);
        rec.addXPoint(1);
        assertEquals(1, rec.getStartX());
        assertEquals(1, rec.getXPoint());
    }

    @Test
    public void testSetY(){
        rec.addStartYPoint(3);
        rec.addYPoint(4);
        assertEquals(3, rec.getStartY());
        assertEquals(4, rec.getYPoint());
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