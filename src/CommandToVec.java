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

    public static ArrayList<String> ConvertToVec(CommandList commandList){
        VecCommands = new ArrayList<>();

        for(Command cmd : commandList){
            penColorHandler(cmd);
            switch(cmd.getCommandType()){
                case PLOT:
                    VecCommands.add(getPlotString(cmd));
                case LINE:
                    VecCommands.add(getLineString(cmd));
                case RECTANGLE:
                    fillColorHandler(cmd);
                    VecCommands.add(getRectangleString(cmd));
                case ELLIPSE:
                    fillColorHandler(cmd);
                    VecCommands.add(getEllipseString(cmd));
                case POLYGON:
                    fillColorHandler(cmd);
                    VecCommands.add(getPolygonString(cmd));
            }
        }
        return VecCommands;
    }

    private static String getPlotString(Command plot){
        return "PLOT " + (plot.getStartX() + " " + plot.getStartY());
    }

    private static String getLineString(Command line){
        return "LINE " + (line.getStartX() + " " + line.getStartY()) + (" " + line.getXPoint() + " " + line.getYPoint());
    }

    private static String getRectangleString(Command rect){
        return "RECTANGLE " + (rect.getStartX() + " " + rect.getStartY()) + (" " + rect.getXPoint() + " " + rect.getStartY());
    }

    private static String getEllipseString(Command ell){
        return "ELLIPSE " + (ell.getStartX() + " " + ell.getStartY()) + (" " + ell.getXPoint() + " " + ell.getStartY());
    }

    private static String getPolygonString(Command poly){
        ArrayList<Integer> xPoints = ((Polygon)poly).getxPoints();
        ArrayList<Integer> yPoints = ((Polygon)poly).getyPoints();
        int pointCount = xPoints.size();

        StringBuilder result = new StringBuilder("POLYGON");
        for(int i = 0; i < pointCount; i++){
            result.append(" ").append(xPoints.get(i)).append(" ").append(yPoints.get(i));
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
            VecCommands.add("PEN " + penColor.toString());
        }
    }

    private static void fillColorHandler(Command cmd){
        Color cmdColor = cmd.getFillColor();

        if(cmdColor == null){
            fillColor = null;
            VecCommands.add("FILL OFF");
        }else if (fillColor != cmdColor){
            fillColor = cmdColor;
            VecCommands.add("FILL " + fillColor.toString());
        }
    }
}
