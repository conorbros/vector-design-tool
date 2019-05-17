package Tests;

import CommandList.CommandList;
import Commands.Ellipse;
import Commands.Line;
import Commands.Plot;
import Commands.Rectangle;
import VecFile.VecFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static VecFile.CommandToVec.ConvertCommandListToVec;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandToVecTests {
    CommandList commands = new CommandList();

    void initCommands(){
        commands.addCommand(new Plot(1000, 1000, Color.BLACK));
        commands.addCommand(new Line(500, 500, 1000, 1000, Color.RED));
        commands.addCommand(new Rectangle(0, 0,1000 , 1000,
                Color.BLACK, Color.WHITE));
        commands.addCommand(new Ellipse(0, 0, 500, 500, Color.WHITE, null));
        commands.addCommand(new Ellipse(0, 0, 500, 500, Color.RED, Color.BLACK));
    }

    @Test
    public void testCommands() throws VecFileException {
        initCommands();
        String result = ConvertCommandListToVec(commands);

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