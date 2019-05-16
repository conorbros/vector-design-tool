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

    private static String convertToString(ArrayList<String> commands) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : commands) {
            stringBuilder.append(str);
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }

    private static String getPlotString(Command plot){
        return "PLOT " + (IntToDecimalConvert(plot.getStartX()) + " " + IntToDecimalConvert(plot.getStartY()));
    }

    private static String getLineString(Command line){
        return "LINE " + (IntToDecimalConvert(line.getStartX()) + " " + IntToDecimalConvert(line.getStartY()) + " " + IntToDecimalConvert(line.getXPoint()) + " " + IntToDecimalConvert(line.getYPoint()));
    }

    private static String getRectangleString(Command rect){
        return "RECTANGLE " + IntToDecimalConvert(rect.getStartX()) + " " + IntToDecimalConvert(rect.getStartY()) + " " + IntToDecimalConvert(rect.getXPoint()) + " " + IntToDecimalConvert(rect.getYPoint());
    }

    private static String getEllipseString(Command ell){
        return "ELLIPSE " + IntToDecimalConvert(ell.getStartX()) + " " + IntToDecimalConvert(ell.getStartY()) + " " + IntToDecimalConvert(ell.getXPoint()) + " " + IntToDecimalConvert(ell.getYPoint());
    }

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

    private static double IntToDecimalConvert(int number){
        return (double)number / screenSize;
    }

    private static void penColorHandler(Command cmd){
        Color cmdColor = cmd.getPenColor();

        if(penColor != cmdColor){
            penColor = cmdColor;
            VecCommands.add("PEN " + getHTMLColorString(penColor));
        }
    }

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

