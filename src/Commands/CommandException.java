package Commands;

public class CommandException extends RuntimeException {
    public CommandException(){
        super();
    }

    public CommandException(String message){
        super(message);
    }

    public CommandException(CommandType command, String message){
        super(command.toString() + " command " + message);
    }
}
