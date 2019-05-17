package VecFile;

import CommandList.CommandList;
import Commands.*;
import Commands.Polygon;
import Commands.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public class VecToCommand {
    private static final int screenSize = 1000;
    private static Color penColor;
    private static Color fillColor;

    /**
     * Converts a vec command string to a command list object
     * @param VecFileStr the vec command string to convert
     * @return a command list object converted from the vec command string
     */
    public static CommandList ConvertVecStrToCommandList(String VecFileStr) throws VecFileException {
        CommandList commands = new CommandList();
        String[] vecCommands = VecFileStr.split("\r\n");
        penColor = Color.BLACK;
        fillColor = null;

        for(String cmd : vecCommands){
            String cmdTypeStr = getSubString(cmd);

            if(cmdTypeStr.equals("PEN")){
                penHandler(cmd);
            }else if(cmdTypeStr.equals("FILL")){
                fillHandler(cmd);
            }else{
                commands.addCommand(convertToCommand(cmdTypeStr, cmd));
            }
        }

        return commands;
    }

    /**
     * Gets the first word in the string, based on where the first space is
     * @param str the string to get the first word of
     * @return a string of the first word from the input
     */
    private static String getSubString(String str){
        return str.substring(0, str.indexOf(" "));
    }

    /**
     * Chooses which method to use to convert the string to a command
     * @param cmdTypeStr The first word of the string (the vec command e.g. LINE)
     * @param cmdStr the full command string, including the first word
     * @return a command object converted from the inputted string
     */
    private static Command convertToCommand(String cmdTypeStr, String cmdStr) throws VecFileException {
        String[] inputs = cmdStr.split(" ");
        switch (cmdTypeStr){
            case "PLOT":
                return plotHandler(inputs);
            case "LINE":
                return lineHandler(inputs);
            case "RECTANGLE":
                return rectangleHandler(inputs);
            case "ELLIPSE":
                return ellipseHandler(inputs);
            case "POLYGON":
                return polygonHandler(inputs);
        }
        return null;
    }

    /**
     * Converts a plot command
     * @param inputs an array of the vec plot command
     * @return a Plot command made from the string array
     */
    private static Command plotHandler(String[] inputs) throws VecFileException {
        int x = IntConvert(inputs[1]);
        int y = IntConvert(inputs[2]);

        Command plot = new Plot(x, y, penColor);
        return plot;
    }

    /**
     * Converts a line command
     * @param inputs an array of the vec line command
     * @return a Line command made from the string array
     */
    private static Command lineHandler(String[] inputs) throws VecFileException {
        int x1 = IntConvert(inputs[1]);
        int y1 = IntConvert(inputs[2]);
        int x2 = IntConvert(inputs[3]);
        int y2 = IntConvert(inputs[4]);

        Command line = new Line(x1, y1, x2, y2, penColor);
        return line;
    }

    /**
     * Converts a rectangle command
     * @param inputs an array of the vec rectangle command
     * @return a Rectangle command made from the string array
     */
    private static Command rectangleHandler(String[] inputs) throws VecFileException {
        int x1 = IntConvert(inputs[1]);
        int y1 = IntConvert(inputs[2]);
        int x2 = IntConvert(inputs[3]);
        int y2 = IntConvert(inputs[4]);

        Command rect = new Rectangle(x1, y1, x2, y2, penColor, fillColor);
        return rect;
    }

    /**
     * Converts an ellipse command
     * @param inputs a string array of the vec ellipse command
     * @return an Ellipse command object
     */
    private static Command ellipseHandler(String[] inputs) throws VecFileException {
        int x1 = IntConvert(inputs[1]);
        int y1 = IntConvert(inputs[2]);
        int x2 = IntConvert(inputs[3]);
        int y2 = IntConvert(inputs[4]);

        Command ell = new Ellipse(x1, y1, x2, y2, penColor, fillColor);
        return ell;
    }

    /**
     * Converts a polygon command
     * @param inputs a string array of the vec polygon command
     * @return a Polygon command object
     */
    private static Command polygonHandler(String[] inputs) throws VecFileException {
        ArrayList<Integer> xPoints = new ArrayList<>();
        ArrayList<Integer> yPoints = new ArrayList<>();

        for (int i = 1; i < inputs.length; i+=2) {
            xPoints.add(IntConvert(inputs[i]));
            yPoints.add(IntConvert(inputs[i+1]));
        }
        Command poly = new Polygon(xPoints, yPoints, penColor, fillColor);
        return poly;
    }

    /**
     * changes the current pen color if the a pen command is in the vec file
     * @param penStr the vec pen command string, containing the color code
     */
    private static void penHandler(String penStr){
        String[] inputs = penStr.split(" ");
        penColor = Color.decode(inputs[1]);
    }

    /**
     * changes the current fill color if the a fill command is in the vec file
     * @param fillStr the vec fill command string, containing the color code
     */
    private static void fillHandler(String fillStr){
        String[] inputs = fillStr.split(" ");

        if(inputs[1].equals("OFF")){
            fillColor = null;
        }else{
            fillColor = Color.decode(inputs[1]);
        }

    }

    /**
     * Converts a vec coordinate to one that can be displayed on the vecPanel
     * @param value the vec coordinate, a string of a double between 0.0 and 1.0
     * @return the inputted double converted to an int between 0 and 1000
     */
    private static int IntConvert(String value) throws VecFileException {
        double dec = Double.parseDouble(value);
        if(dec < 0 || dec > 1000) throw new VecFileException("Coordinate is outside of vecPanel bounds");
        return (int) (dec * screenSize);
    }
}

