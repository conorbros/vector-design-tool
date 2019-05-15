package VecFile;

import CommandList.CommandList;
import GUI.VECPanel;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static VecFile.VecToCommand.ConvertVecStrToCommandList;

public class VecFileInput {

    public static void LoadVecFile(File vecFile, VECPanel vecPanel){
        try {
            String vecFileStr = readFile(vecFile);
            CommandList commands = ConvertVecStrToCommandList(vecFileStr);
            vecPanel.loadCommandList(commands);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
