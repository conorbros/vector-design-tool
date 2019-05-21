package Commands;

public class CommandException extends RuntimeException {

    /**
     * Throws a command exception
     * @param command The CommandType throwing the exception
     * @param message The Message for the exception
     */
    public CommandException(CommandType command, String message){
        super(command.toString() + " command " + message);
    }
}
