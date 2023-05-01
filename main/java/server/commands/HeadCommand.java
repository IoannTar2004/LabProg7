package server.commands;

import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.List;

/**
 * Prints the first object in collection if it is not empty.
 */
public class HeadCommand implements Command {

    /**
     * Prints the first object in collection if it is not empty.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        ObjectsManager objectsManager = new ObjectsManager();
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        if (objectsManager.length() > 0) {
            return new ServerSender(List.of(getters.getDragonByIndex(0).toString()));
        } else {
            return new ServerSender(List.of(OutputText.result("Empty")));
        }
    }
}
