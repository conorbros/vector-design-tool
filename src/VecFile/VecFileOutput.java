package VecFile;

import CommandList.CommandList;

import java.io.*;
import static VecFile.CommandToVec.ConvertCommandListToVec;

public class VecFileOutput {

    /**
     * Converts and puts a CommandList object into a new vec file
     * @param commands the CommandList object to write to the vec file
     * @param path the path to write the file to
     * @throws FileNotFoundException thrown if the file is not found
     */
    public static File CommandsToNewVecFile(CommandList commands, String path) throws FileNotFoundException {
        String commandsOutput;
        commandsOutput = ConvertCommandListToVec(commands);

        File file = new File(path);
        file.getParentFile().mkdirs();
        PrintWriter out = new PrintWriter(file);

        out.println(commandsOutput);
        out.close();
        return file;
    }

    /**
     * Converts and writes a command list object to an existing vec file
     * @param commands the command list object to write to the vec file
     * @param file the file to write the command list object to
     * @throws FileNotFoundException Thrown if the VECFile is not found
     */
    public static void CommandsToExistingVecFile(CommandList commands, File file) throws FileNotFoundException{
        String commandsOutput = ConvertCommandListToVec(commands);
        PrintWriter out = new PrintWriter(file);
        out.println(commandsOutput);
        out.close();
    }
}
