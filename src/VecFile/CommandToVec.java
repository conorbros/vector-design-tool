package VecFile;

import CommandList.CommandList;
import Commands.Command;
import Commands.Polygon;
import java.awt.*;
import java.util.ArrayList;

public class CommandToVec {
    private static final int screenSize = 1000;
    private static Color penColor;
    private static Color fillColor;
    private static ArrayList<String> VecCommands;

    /**
     * Converts a command list to vec commands in a string, ready to be inputted into the file
     * @param commandList the command list to be converted
     * @return a string of the commands in the commandlist as vec file ready commands
     */
    public static String ConvertCommandListToVec(CommandList commandList){
        VecCommands = new ArrayList<>();
        penColor = Color.BLACK;
        fillColor = null;

        for(Command cmd : commandList){
            penColorHandler(cmd);
            switch(cmd.getCommandType()){
                case PLOT:
                    VecCommands.add(getPlotString(cmd));
                    break;
                case LINE:
                    VecCommands.add(getLineString(cmd));
                    break;
                case RECTANGLE:
                    fillColorHandler(cmd);
                    VecCommands.add(getRectangleString(cmd));
                    break;
                case ELLIPSE:
                    fillColorHandler(cmd);
                    VecCommands.add(getEllipseString(cmd));
                    break;
                case POLYGON:
                    fillColorHandler(cmd);
                    VecCommands.add(getPolygonString(cmd));
                    break;
            }
        }

        return convertToString(VecCommands);
    }

    /**
     * Converts an arraylist of type String to one single string
     * @param commands the Arraylist
     * @return a string of all the strings in the commandlist
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
     * Converts a Plot command object to a vec command string
     * @param plot the Plot object to convert
     * @return the vec command for the object as a string
     */
    private static String getPlotString(Command plot){
        return "PLOT " + (IntToDecimalConvert(plot.getStartX()) + " " + IntToDecimalConvert(plot.getStartY()));
    }

    /**
     * Converts a Line command object to a vec command string
     * @param line the Line object to convert
     * @return the vec command for the object as a string
     */
    private static String getLineString(Command line){
        return "LINE " + (IntToDecimalConvert(line.getStartX()) + " " + IntToDecimalConvert(line.getStartY()) + " " + IntToDecimalConvert(line.getXPoint()) + " " + IntToDecimalConvert(line.getYPoint()));
    }

    /**
     * Converts a Rectangle command object to a vec command string
     * @param rect the Rectangle object to convert
     * @return the vec command for the object as a string
     */
    private static String getRectangleString(Command rect){
        return "RECTANGLE " + IntToDecimalConvert(rect.getStartX()) + " " + IntToDecimalConvert(rect.getStartY()) + " " + IntToDecimalConvert(rect.getXPoint()) + " " + IntToDecimalConvert(rect.getYPoint());
    }

    /**
     * Converts a ellipse command object to a vec command
     * @param ell the ellipse object to convert
     * @return a string of the vec command for the ellipse object
     */
    private static String getEllipseString(Command ell){
        return "ELLIPSE " + IntToDecimalConvert(ell.getStartX()) + " " + IntToDecimalConvert(ell.getStartY()) + " " + IntToDecimalConvert(ell.getXPoint()) + " " + IntToDecimalConvert(ell.getYPoint());
    }

    /**
     * Converts a polygon command object to a vec command
     * @param poly the polygon object to convert
     * @return a string of the vec command for the polygon object
     */
    private static String getPolygonString(Command poly){
        ArrayList<Integer> xPoints = ((Polygon)poly).getxPoints();
        ArrayList<Integer> yPoints = ((Polygon)poly).getyPoints();
        int pointCount = xPoints.size();

        StringBuilder result = new StringBuilder("POLYGON");
        for(int i = 0; i < pointCount; i++){
            result.append(" ").append(IntToDecimalConvert(xPoints.get(i))).append(" ").append(IntToDecimalConvert(yPoints.get(i)));
        }
        return result.toString();
    }

    /**
     * Scales the vecPanel coordinate to a vec coordinate 0.0-1.0
     * @param number The coordinate to convert
     * @return a double between 0.0 and 1.0
     */
    private static double IntToDecimalConvert(int number){
        return (double)number / screenSize;
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

