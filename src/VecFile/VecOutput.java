package VecFile;

import CommandList.CommandList;

import java.io.*;
import java.util.ArrayList;

import static VecFile.CommandToVec.ConvertToVec;

public class VecOutput {

    public static void CommandsToVecFile(CommandList commands, String path, String fileName) throws FileNotFoundException {
        ArrayList<String> commandsOutput = ConvertToVec(commands);

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : commandsOutput) {
            stringBuilder.append(str);
            stringBuilder.append(" ");
        }
        String outputString = stringBuilder.toString();

        File file = new File(path + "/" + fileName + ".vec");
        file.getParentFile().mkdirs();
        PrintWriter out = new PrintWriter(file);

        out.println(outputString);
        out.close();
    }
}
