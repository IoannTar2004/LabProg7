package client.readers;

import org.example.tools.Checks;
import org.example.tools.FileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScriptReader {
    /**
     * Reads entered script file with commands.
     * @param file script file
     * @return list of commands
     */
    public static List<String> read(File file) {
        List<String> commands = new ArrayList<>();
        String command;

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            do {
                command = buffer.readLine();
                if (Objects.equals(command.split("\\s+")[0], "execute_script")) {
                    try {
                        File file1 = new Checks(command.split("\\s+")[1]).fileChecker();
                        if (FileManager.addFileToStack(file1)) {
                            commands.addAll(read(file1));
                        } else {
                            commands.add("ScriptRecursion");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        commands.add("NoFileArgument");
                    } catch (FileNotFoundException e) {
                        commands.add("FileNotFound");
                    }
                } else {
                    commands.add(command);
                }
            } while (command != null);
            buffer.close();
        } catch (Exception ignored) {}
        return commands;
    }
}
