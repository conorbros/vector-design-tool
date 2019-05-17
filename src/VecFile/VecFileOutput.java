package VecFile;

import CommandList.CommandList;

import java.io.*;
import static VecFile.CommandToVec.ConvertCommandListToVec;

public class VecFileOutput {

    /**
     * Converts and puts a commandlist object into a new vec file
     * @param commands the commandslist object to write to the vec file
     * @param path the path to write the file to
     * @param fileName the name of the new file
     * @throws FileNotFoundException
     */
    public static void CommandsToNewVecFile(CommandList commands, String path, String fileName) throws FileNotFoundException {
        String commandsOutput;
        try {
            commandsOutput = ConvertCommandListToVec(commands);
        } catch (VecFileException e) {
            e.printStackTrace();
            return;
        }

        File file = new File(path + "/" + fileName + ".vec");
        file.getParentFile().mkdirs();
        PrintWriter out = new PrintWriter(file);

        out.println(commandsOutput);
        out.close();
    }

    /**
     * Converts and writes a command list object to an existing vec file
     * @param commands the command list object to write to the vec file
     * @param file the file to write the command list object to
     * @throws FileNotFoundException
     */
    public static void CommandsToExistingVecFile(CommandList commands, File file) throws FileNotFoundException, VecFileException {
        String commandsOutput = null;
        commandsOutput = ConvertCommandListToVec(commands);


        PrintWriter out = new PrintWriter(file);
        out.println(commandsOutput);
        out.close();
    }
}
