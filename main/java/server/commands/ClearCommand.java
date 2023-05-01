package server.commands;

import org.example.tools.OutputText;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.List;

/**
 * Clears collection.
 */
public class ClearCommand implements Command {
    /**
     * Clears collection.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        ObjectsManager objectsManager = new ObjectsManager();

        objectsManager.clear();
        return new ServerSender(List.of(OutputText.result("Cleared")));
    }
}
