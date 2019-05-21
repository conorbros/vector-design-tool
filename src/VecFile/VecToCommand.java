package VecFile;

import CommandList.CommandList;
import Commands.*;
import Commands.Polygon;
import Commands.Rectangle;

import java.awt.*;
import java.util.ArrayList;

class VecToCommand {
    private static int screenSize;
    private static Color penColor, fillColor;

    /**
     * Converts a vec command string to a CommandList object
     * @param VecFileStr the VEC command string to convert
     * @return a CommandList object converted from the VEC command string
     */
    static CommandList ConvertVecStrToCommandList(String VecFileStr, int screenSz) throws VecFileException {
        CommandList commands = new CommandList();
        String[] vecCommands = VecFileStr.split("\r\n");

        // default pen and fill color
        penColor = Color.BLACK;
        fillColor = null;

        screenSize = screenSz;

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
        throw new VecFileException("Invalid command in vec file", "There is an invalid command in your vec file", "Invalid vec file");
    }

    /**
     * Converts a plot command
     * @param inputs an array of the vec plot command
     * @return a Plot command made from the string array
     */
    private static Command plotHandler(String[] inputs) throws VecFileException {
        double x = ParseDouble(inputs[1]);
        double y = ParseDouble(inputs[2]);

        return new Plot(x, y, penColor, screenSize);
    }

    /**
     * Converts a line command
     * @param inputs an array of the vec line command
     * @return a Line command made from the string array
     */
    private static Command lineHandler(String[] inputs) throws VecFileException {
        double x1 = ParseDouble(inputs[1]);
        double y1 = ParseDouble(inputs[2]);
        double x2 = ParseDouble(inputs[3]);
        double y2 = ParseDouble(inputs[4]);

        return new Line(x1, y1, x2, y2, penColor, screenSize);
    }

    /**
     * Converts a rectangle command
     * @param inputs an array of the vec rectangle command
     * @return a Rectangle command made from the string array
     */
    private static Command rectangleHandler(String[] inputs) throws VecFileException {
        double x1 = ParseDouble(inputs[1]);
        double y1 = ParseDouble(inputs[2]);
        double x2 = ParseDouble(inputs[3]);
        double y2 = ParseDouble(inputs[4]);

        return new Rectangle(x1, y1, x2, y2, penColor, fillColor, screenSize);
    }

    /**
     * Converts an ellipse command
     * @param inputs a string array of the vec ellipse command
     * @return an Ellipse command object
     */
    private static Command ellipseHandler(String[] inputs) throws VecFileException {
        double x1 = ParseDouble(inputs[1]);
        double y1 = ParseDouble(inputs[2]);
        double x2 = ParseDouble(inputs[3]);
        double y2 = ParseDouble(inputs[4]);

        return new Ellipse(x1, y1, x2, y2, penColor, fillColor, screenSize);
    }

    /**
     * Converts a polygon command
     * @param inputs a string array of the vec polygon command
     * @return a Polygon command object
     */
    private static Command polygonHandler(String[] inputs) throws VecFileException {
        ArrayList<Double> xPoints = new ArrayList<>();
        ArrayList<Double> yPoints = new ArrayList<>();

        for (int i = 1; i < inputs.length; i+=2) {
            xPoints.add(ParseDouble(inputs[i]));
            yPoints.add(ParseDouble(inputs[i+1]));
        }
        return new Polygon(xPoints, yPoints, penColor, fillColor, screenSize);
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
     * Parses a double from a VEC command string substring
     * @param value The string value to be parsed
     * @return A double parsed from the string
     * @throws VecFileException Thrown if the coordinate is outside the bounds of VEC coordinates
     */
    private static double ParseDouble(String value) throws VecFileException {
        double dbl = Double.parseDouble(value);
        if(dbl < 0.0 || dbl > 1.0) {
            throw new VecFileException("Coordinate is outside of vecPanel bounds");
        }
        return dbl;
    }
}

