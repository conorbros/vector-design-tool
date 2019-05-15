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

    public static CommandList ConvertVecStrToCommandList(String VecFileStr){
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

    private static String getSubString(String str){
        return str.substring(0, str.indexOf(" "));
    }

    private static Command convertToCommand(String cmdTypeStr, String cmdStr){
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

    private static Command plotHandler(String[] inputs){
        int x = IntConvert(inputs[1]);
        int y = IntConvert(inputs[2]);

        Command plot = new Plot(x, y, penColor);
        return plot;
    }

    private static Command lineHandler(String[] inputs){
        int x1 = IntConvert(inputs[1]);
        int y1 = IntConvert(inputs[2]);
        int x2 = IntConvert(inputs[3]);
        int y2 = IntConvert(inputs[4]);

        Command line = new Line(x1, y1, x2, y2, penColor);
        return line;
    }

    private static Command rectangleHandler(String[] inputs){
        int x1 = IntConvert(inputs[1]);
        int y1 = IntConvert(inputs[2]);
        int x2 = IntConvert(inputs[3]);
        int y2 = IntConvert(inputs[4]);

        Command rect = new Rectangle(x1, y1, x2, y2, penColor, fillColor);
        return rect;
    }

    private static Command ellipseHandler(String[] inputs){
        int x1 = IntConvert(inputs[1]);
        int y1 = IntConvert(inputs[2]);
        int x2 = IntConvert(inputs[3]);
        int y2 = IntConvert(inputs[4]);

        Command ell = new Ellipse(x1, y1, x2, y2, penColor, fillColor);
        return ell;
    }

    private static Command polygonHandler(String[] inputs){
        ArrayList<Integer> xPoints = new ArrayList<>();
        ArrayList<Integer> yPoints = new ArrayList<>();

        for (int i = 1; i < inputs.length; i+=2) {
            xPoints.add(IntConvert(inputs[i]));
            yPoints.add(IntConvert(inputs[i+1]));
        }
        Command poly = new Polygon(xPoints, yPoints, penColor, fillColor);
        return poly;
    }

    private static void penHandler(String penStr){
        String[] inputs = penStr.split(" ");
        penColor = Color.decode(inputs[1]);
    }

    private static void fillHandler(String fillStr){
        String[] inputs = fillStr.split(" ");

        if(inputs[1].equals("OFF")){
            fillColor = null;
        }else{
            fillColor = Color.decode(inputs[1]);
        }

    }

    private static int IntConvert(String value){
        double dec = Double.parseDouble(value);
        return (int) (dec * screenSize);
    }
}

