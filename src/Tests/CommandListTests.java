package Tests;

import CommandList.CommandList;
import Commands.Command;
import Commands.Line;
import Commands.Plot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static Enums.CommandType.LINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandListTests {
    private CommandList list;
    private int size = 1000;

    @BeforeEach
    void beforeEach(){
        list = null;
        list = new CommandList();
    }

    @Test
    void testAddCommand(){
        list.addCommand(new Line(1, 1, Color.BLACK, size));
    }

    @Test
    void testGetLastCommand(){
        list.addCommand(new Line(1, 1, Color.BLACK, size));
        list.addCommand(new Line(2, 2, 3, 3, Color.BLUE, size));
        Plot plot = new Plot(1, 1, Color.BLACK, size);
        list.addCommand(plot);
        assertEquals(plot, list.getLastCommand());
    }

    @Test
    void getAllAfter(){
        addCommandsToList(list, 30);
        Plot plot = new Plot(1, 1, Color.BLACK, size);
        list.addCommand(plot);
        CommandList result = list.getAllAfter(plot);
        Boolean res = true;

        for(Command cmd: result){
            if(cmd.getCommandType() != LINE){
                res = false;
            }
        }
        assertTrue(res);
    }

    @Test
    void testRemoveCommand(){
        addCommandsToList(list, 30);
        Plot plot = new Plot(1, 1, Color.BLACK, size);
        list.addCommand(plot);
        list.removeCommand(plot);
        assertEquals(LINE, list.getLastCommand().getCommandType());
    }

    @Test
    void testRemoveAllAfter(){
        Plot plot = new Plot(1, 1, Color.BLACK, size);
        list.addCommand(plot);
        addCommandsToList(list, 30);
        list.removeAllAfter(plot);
        assertEquals(plot, list.getLastCommand());
    }

    @Test
    void testRemoveLast(){
        addCommandsToList(list, 20);
        Plot plot = new Plot(1, 1, Color.BLACK, size);
        list.addCommand(plot);
        list.removeLastCommand();
        assertEquals(LINE, list.getLastCommand().getCommandType());
    }

    @Test
    void testToArray(){
        addCommandsToList(list, 20);
        assertEquals(list.toArray().length, 20);
    }

    @Test
    void testAddAll(){
        CommandList newList = new CommandList();
        addCommandsToList(newList, 30);
        list.addAll(newList);
        assertEquals(LINE, list.getLastCommand().getCommandType());
    }

    private void addCommandsToList(CommandList list, int count){
        Command cmd;
        for(int i = 0; i < count; i++){
            cmd = new Line(1, 1, Color.WHITE, size);
            list.addCommand(cmd);
        }
    }
}
