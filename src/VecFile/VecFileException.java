package VecFile;

import javax.swing.*;
import java.awt.*;

public class VecFileException extends Exception {

    public VecFileException(String message){
        super(message);
    }

    public VecFileException(String message, String dialogMessage, String dialogTitle){
        super(message);
        JOptionPane.showMessageDialog(new JFrame(), dialogMessage, dialogTitle, JOptionPane.ERROR_MESSAGE);
    }
}
