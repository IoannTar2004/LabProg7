package server.commands;

import org.example.collections.DragonFields;
import server.modules.ServerSender;
import server.tools.Sort;

import java.util.Objects;

/**
 * Prints objects in descending order by its value of field.
 */
public class PrintDescendingCommand implements Command {
    private static String[] arguments;
    /**
     * Prints objects in descending order by its value of field.
     */
    @Override
    public ServerSender<String> execute(String mode, String[] command, String login, Object... args) {
        if (Objects.equals(mode, "script")) {
            executeWithScript(command, (String) args[0]);
        } else if (Objects.equals(mode, "collection")) {
            return new ServerSender<>(Sort.sort((DragonFields) args[1], login, arguments));
        } else {
            arguments = command;
            return new ServerSender<>(new String[]{"fieldSelection"});
        }
        return null;
    }

    /**
     * Triggers when command is from script file. Object is not created if at least one of the argument is invalid.
     * @param arg number of field
     */
    public static void executeWithScript(String[] command, String arg) {
        DragonFields fieldNum;

        fieldNum = DragonFields.getFieldByNumber(arg);
        assert fieldNum != null;
        //Sort.sort(fieldNum, command);
    }
}
