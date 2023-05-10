package server.commands;

import org.example.tools.OutputText;
import server.database.DataBaseStuds;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.List;

/**
 * Removes the first object in collection.
 */
public class RemoveFirstCommand implements Command {
    /**
     * Removes the first object in collection.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        ObjectsManager objectsManager = new ObjectsManager();
        try {
            new DataBaseStuds().removeFirst((String) args[0]);
            //objectsManager.remove_first();
            return new ServerSender(List.of(OutputText.result("RemovedFirst")));
        } catch (NullPointerException e) {
            return new ServerSender(List.of(OutputText.result("Empty")));
        }
    }
}
