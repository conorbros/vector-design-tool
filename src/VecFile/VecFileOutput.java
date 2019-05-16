package VecFile;

import CommandList.CommandList;

import java.io.*;
import java.util.ArrayList;

import static VecFile.CommandToVec.ConvertCommandListToVec;

public class VecFileOutput {

    public static void CommandsToNewVecFile(CommandList commands, String path, String fileName) throws FileNotFoundException {
        String commandsOutput = ConvertCommandListToVec(commands);

        File file = new File(path + "/" + fileName + ".vec");
        file.getParentFile().mkdirs();
        PrintWriter out = new PrintWriter(file);

        out.println(commandsOutput);
        out.close();
    }

    public static void CommandsToExistingVecFile(CommandList commands, File file) throws FileNotFoundException {
        String commandsOutput = ConvertCommandListToVec(commands);

        PrintWriter out = new PrintWriter(file);
        out.println(commandsOutput);
        out.close();
    }
}
