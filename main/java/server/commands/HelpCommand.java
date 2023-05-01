package server.commands;

import org.example.tools.OutputText;
import server.modules.ServerSender;

import java.util.List;

/**
 * Prints all commands.
 */
public class HelpCommand implements Command {
    /**
     * Prints all commands.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        return new ServerSender(List.of(OutputText.result("AllCommands")));
    }
}
