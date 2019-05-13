package Tests;

import CommandList.CommandList;
import Commands.Command;
import Commands.Line;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandListTests {
    private CommandList list;

    @BeforeEach
    public void beforeEach(){
        list = null;
        list = new CommandList();
    }

    @Test
    public void testAddCommand(){
        list.addCommand(new Line(1, 1, Color.BLACK));
    }

    @Test
    public void testCount(){
        addCommandsToList(list, 20);
        assertEquals(20, list.Count());
    }

    @Test
    public void testremoveLast(){
        addCommandsToList(list, 20);
        list.removeLastCommand();
        assertEquals(19, list.Count());
    }

    private void addCommandsToList(CommandList list, int count){
        Command cmd;
        for(int i = 0; i < count; i++){
            cmd = new Line(1, 1, Color.WHITE);
            list.addCommand(cmd);
        }
    }
}
