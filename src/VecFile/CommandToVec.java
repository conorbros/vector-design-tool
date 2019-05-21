package VecFile;

import CommandList.CommandList;
import Commands.Command;
import java.awt.*;
import java.util.ArrayList;

import static Commands.CommandType.LINE;
import static Commands.CommandType.PLOT;

class CommandToVec {
    private static Color penColor, fillColor;
    private static ArrayList<String> VecCommands;

    /**
     * Converts a command list to vec commands in a string, ready to be inputted into the file
     * @param commandList the command list to be converted
     * @return a string of the commands in the command list as vec file ready commands
     */
    static String ConvertCommandListToVec(CommandList commandList){
        VecCommands = new ArrayList<>();
        penColor = Color.BLACK;
        fillColor = null;

        for(Command cmd : commandList){
            penColorHandler(cmd);
            if(cmd.getCommandType() != LINE && cmd.getCommandType() != PLOT){
                fillColorHandler(cmd);
            }
            VecCommands.add(cmd.toVEC());
        }

        return convertToString(VecCommands);
    }

    /**
     * Converts an ArrayList of type String to one single string
     * @param commands the ArrayList
     * @return a string of all the strings in the CommandList
     */
    private static String convertToString(ArrayList<String> commands) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : commands) {
            stringBuilder.append(str);
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Inputs a pen color into the vec command list if the next command object has a different pen color
     * @param cmd the next command object to be converted
     */
    private static void penColorHandler(Command cmd){
        Color cmdColor = cmd.getPenColor();

        if(penColor != cmdColor){
            penColor = cmdColor;
            VecCommands.add("PEN " + getHTMLColorString(penColor));
        }
    }

    /**
     * Inputs a fill color into the vec command list if the next command object has a different fill color
     * @param cmd the next command object to be converted
     */
    private static void fillColorHandler(Command cmd){
        Color cmdColor = cmd.getFillColor();

        if (fillColor != cmdColor){
            if(cmdColor == null){
                fillColor = null;
                VecCommands.add("FILL OFF");
                return;
            }
            fillColor = cmdColor;
            VecCommands.add("FILL " + getHTMLColorString(fillColor));
        }
    }

    /**
     * Converts a Java Color Object to a HEX code
     * @param color the color to convert
     * @return a string of the HEX code of the color
     */
    private static String getHTMLColorString(Color color) {
        String red = Integer.toHexString(color.getRed());
        String green = Integer.toHexString(color.getGreen());
        String blue = Integer.toHexString(color.getBlue());

        return "#" +
                (red.length() == 1? "0" + red : red) +
                (green.length() == 1? "0" + green : green) +
                (blue.length() == 1? "0" + blue : blue);
    }
}

