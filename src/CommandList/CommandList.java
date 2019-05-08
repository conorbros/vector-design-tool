package CommandList;

import Commands.Command;
import Commands.CommandException;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandList implements Iterable<Command> {
    private ArrayList<Command> commands;

    public CommandList(){
        commands = new ArrayList<>();
    }

    public int count(){
        return commands.size()-1;
    }

    public void addCommand(Command cmd){
        commands.add(cmd);
    }

    public Command getLastCommand(){
        if(commands.size() > 0) {
            return commands.get(commands.size() - 1);
        }else{
            return null;
        }
    }

    public void removeLastCommand(){
        if(commands.size() > 0){
            commands.remove(commands.size()-1);
        }
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}
