package Tests;

import Commands.Plot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class PlotTests {
    private Plot plot;

    @BeforeEach
    public void beforeEach(){
        plot = null;
        plot = new Plot(1,1, Color.BLACK);
    }

    @Test
    public void testSetX(){
        plot = new Plot(1, 1, Color.BLACK);

        assertEquals(1, plot.getX1());
    }

    @Test
    public void testSetY(){
        plot = new Plot(1, 1, Color.BLACK);
        assertEquals(1, plot.getY1());
    }
}
