package VecFile;

import CommandList.CommandList;
import GUI.VECPanel;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static VecFile.VecToCommand.ConvertVecStrToCommandList;

public class VecFileInput {

    /**
     * Loads the supplied vec file into the supplied vecPanel object
     * @param vecFile the vec file to load
     * @param vecPanel the vec panel object to load the vec file into
     */
    public static void LoadVecFile(File vecFile, VECPanel vecPanel){
        try {
            String vecFileStr = readFile(vecFile);
            CommandList commands = ConvertVecStrToCommandList(vecFileStr);
            vecPanel.loadCommandList(commands);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads a file and converts its contents to a string
     * @param file the file to read
     * @return a string of the files contents
     * @throws IOException
     */
    private static String readFile(File file) throws IOException {
        StringBuilder fileContents = new StringBuilder((int)file.length());

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();
        }
    }
}
