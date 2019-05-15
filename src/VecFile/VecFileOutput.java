package VecFile;

import CommandList.CommandList;

import java.io.*;
import java.util.ArrayList;

import static VecFile.CommandToVec.ConvertCommandListToVec;

public class VecFileOutput {

    public static void CommandsToNewVecFile(CommandList commands, String path, String fileName) throws FileNotFoundException {
        ArrayList<String> commandsOutput = ConvertCommandListToVec(commands);
        String outputString = commandsArrayToString(commandsOutput);

        File file = new File(path + "/" + fileName + ".vec");
        file.getParentFile().mkdirs();
        PrintWriter out = new PrintWriter(file);

        out.println(outputString);
        out.close();
    }

    public static void CommandsToExistingVecFile(CommandList commands, File file) throws FileNotFoundException {
        ArrayList<String> commandsOutput = ConvertCommandListToVec(commands);
        String outputString = commandsArrayToString(commandsOutput);

        PrintWriter out = new PrintWriter(file);
        out.println(outputString);
        out.close();
    }

    private static String commandsArrayToString(ArrayList<String> commands){
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : commands) {
            stringBuilder.append(str);
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }
}
