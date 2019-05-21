package CommandList;

import Commands.Command;
import java.util.*;

public class CommandList implements Iterable<Command> {
    private ArrayList<Command> commands;

    /**
     * Initializes a new CommandList object
     */
    public CommandList(){
        commands = new ArrayList<>();
    }

    /**
     * adds a command to the CommandList object
     * @param cmd the command to add to the CommandList object
     */
    public void addCommand(Command cmd){
        commands.add(cmd);
    }

    /**
     * Returns the last command in the CommandList object
     * @return The last command in the CommandList object
     */
    public Command getLastCommand(){
        if(commands.size() > 0) {
            return commands.get(commands.size() - 1);
        }else{
            return null;
        }
    }

    /**
     * Returns a CommandList of all the commands after the supplied command in this CommandList object
     * @param cmd the Command to return all the commands after
     * @return A CommandList of all the commands after the supplied Command
     */
    public CommandList getAllAfter(Command cmd){
        CommandList cmdList = new CommandList();
        int position = commands.indexOf(cmd);
        int count = commands.size()-1;
        for(int i = count; i > position; i--){
            cmdList.addCommand(commands.get(i));
        }
        return cmdList;
    }

    /**
     * Removes the supplied Command from this CommandList
     * @param cmd The Command to remove from the CommandList
     */
    public void removeCommand(Command cmd){
        commands.remove(cmd);
    }

    /**
     * Removes all the Commands after the the supplied Command in the CommandList
     * @param cmd The Command to remove all the Commands that come after it in the CommandList
     */
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

    /**
     * Converts the CommandList object to an array of type Object[]
     * @return An array of type Object[]
     */
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

    /**
     *  Adds the supplied CommandList to this CommandList object
     * @param commandList the CommandList to add
     */
    public void addAll(CommandList commandList) {
        for(Command cmd : commandList){
            commands.add(cmd);
        }
    }
}
