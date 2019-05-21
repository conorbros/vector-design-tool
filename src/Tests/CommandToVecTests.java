package Tests;

import CommandList.CommandList;
import Commands.Ellipse;
import Commands.Line;
import Commands.Plot;
import Commands.Rectangle;
import VecFile.CommandToVec;
import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandToVecTests {
    CommandList commands = new CommandList();
    private int size = 1000;

    void initCommands(){
        commands.addCommand(new Plot(1.0, 1.0, Color.BLACK, size));
        commands.addCommand(new Line(0.5, 0.5, 1.0, 1.0, Color.RED, size));
        commands.addCommand(new Rectangle(0.0, 0.0,1.0, 1.0,
                Color.BLACK, Color.WHITE, size));
        commands.addCommand(new Ellipse(0.0, 0.0, 0.5, 0.5, Color.WHITE, null, size));
        commands.addCommand(new Ellipse(0.0, 0.0, 0.5, 0.5, Color.RED, Color.BLACK, size));
    }

    @Test
    public void testCommands(){
        initCommands();
        String result = CommandToVec.ConvertCommandListToVec(commands);

        System.out.println(result);
        String expected = "PLOT 1.0 1.0\r\n";
        expected += "PEN #ff0000\r\n";
        expected += "LINE 0.5 0.5 1.0 1.0\r\n";
        expected += "PEN #000000\r\n";
        expected += "FILL #ffffff\r\n";
        expected += "RECTANGLE 0.0 0.0 1.0 1.0\r\n";
        expected += "PEN #ffffff\r\n";
        expected += "FILL OFF\r\n";
        expected += "ELLIPSE 0.0 0.0 0.5 0.5\r\n";
        expected += "PEN #ff0000\r\n";
        expected += "FILL #000000\r\n";
        expected += "ELLIPSE 0.0 0.0 0.5 0.5\r\n";

        assertEquals(expected, result);
    }
}