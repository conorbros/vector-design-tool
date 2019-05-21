package VecFile;

import javax.swing.*;
import java.awt.*;

public class VecFileException extends Exception {

    /**
     * Throws a new VECFileException
     * @param message The message to display for the exception
     */
    VecFileException(String message){
        super(message);
    }

    /**
     * Throws a new VECFileException
     * @param message The message to display for the exception
     * @param dialogMessage The dialog message to display to the user
     * @param dialogTitle The dialog title to display to the user
     */
    VecFileException(String message, String dialogMessage, String dialogTitle){
        super(message);
        JOptionPane.showMessageDialog(new JFrame(), dialogMessage, dialogTitle, JOptionPane.ERROR_MESSAGE);
    }
}
