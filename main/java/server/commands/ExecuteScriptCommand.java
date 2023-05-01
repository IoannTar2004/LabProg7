package server.commands;

import org.example.tools.Checks;
import org.example.tools.OutputText;
import server.modules.ServerInvoker;
import server.modules.ServerReader;
import server.modules.ServerSender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * creates the list of commands received from txt file and runs.
 */
public class ExecuteScriptCommand implements Command{

    public ExecuteScriptCommand(){}

    /**
     * Creates the list of commands received from txt file and runs.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        if (Objects.equals(mode, "script")) {
            List<String> commands = (List<String>) args[0];
            List<String> result = new LinkedList<>();
            List<Object> argument = new LinkedList<>();
            for(int i = 0; i < 8; i++) {
                commands.add(null);
            }
            int i = 0;
            while (!Objects.equals(commands.get(i), null)) {
                String[] currentCommand = commands.get(i).split("\\s+");
                ServerSender sender = ServerInvoker.invoke(ServerReader.getCommand(currentCommand[0]),
                        "script", currentCommand,
                        commands.get(i + 1), commands.get(i + 2),
                        commands.get(i + 3), commands.get(i + 4),
                        commands.get(i + 5), commands.get(i + 6),
                        commands.get(i + 7), commands.get(i + 8));
                if (sender.getResult() != null) {
                    result.addAll(sender.getResult());
                }
                if (sender.getArguments() != null) {
                    argument.addAll(Arrays.asList(sender.getArguments()));
                }
                replaceTextErrors(commands.get(i), result);
                i++;
            }
            return new ServerSender(result, argument.toArray());
        }

        return null;
    }

    private static void replaceTextErrors(String error, List<String> result) {
        try {
            result.add(OutputText.error(error));
        } catch (MissingResourceException ignored) {} //method skip
    }
}
