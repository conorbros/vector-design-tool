package CommandList;

import Commands.Command;

import java.util.ArrayList;
import java.util.Iterator;

public class CommandList implements Iterable<Command> {
    private ArrayList<Command> commands;

    public CommandList(){
        commands = new ArrayList<>();
    }

    /**
     * the count of the commands in the CommandList object
     * @return int of the count
     */
    public int Count(){
        return commands.size();
    }

    /**
     * adds a command to the CommandList object
     * @param cmd the command to add to the CommandList object
     */
    public void addCommand(Command cmd){
        commands.add(cmd);
    }

    /**
     * gets the last command in the CommandList object
     * @return the last command in the CommandList object
     */
    public Command getLastCommand(){
        if(commands.size() > 0) {
            return commands.get(commands.size() - 1);
        }else{
            return null;
        }
    }

    public Command get(int index){
        return commands.get(index);
    }

    public void removeCommand(Command cmd){
        commands.remove(cmd);
    }

    public void removeAllAfter(Command cmd){
        int position = commands.indexOf(cmd);
        int count = commands.size()-1;
        for(int i = count; i > position; i--){
            commands.remove(i);
        }
    }

    /**
     * removes the last command from the CommandList object
     */
    public void removeLastCommand(){
        if(commands.size() > 0){
            commands.remove(commands.size()-1);
        }
    }

    public Object[] toArray(){
        return commands.toArray();
    }

    /**
     * Uses the ArrayList iterator for the CommandList object
     * @return iterator
     */
    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}
