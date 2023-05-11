package server.commands;

import org.example.collections.Dragon;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsElements;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.LinkedList;
import java.util.List;

/**
 * Prints all objects in collection
 */
public class ShowCommand implements Command {

    /**
     * Prints objects in collection. If arguments are absent it prints all elements.
     * It can print some fields in relation to numbers.
     */
    @Override
    public ServerSender<String> execute(String mode, String[] command, String login, Object... args) {
        List<String> list = new LinkedList<>();
        ObjectsManager objectsManager = new ObjectsManager();
        ObjectsElements objectsElements = new ObjectsElements();
        ObjectsCollectionManager getters = new ObjectsCollectionManager();
        List<Dragon> dragonList = new LinkedList<>(getters.getAll());

        if (objectsManager.fullLength() > 0) {
            for (int i = 0; i < objectsManager.fullLength(); i++) {
                if (login.equals(dragonList.get(i).getUserLogin())) {
                    list.add(objectsElements.element(getters.getDragonByIndex(i), command));
                }
            }
        }
        return list.size() > 0 ? new ServerSender<>(list) : new ServerSender<>(List.of(OutputText.result("Empty")));
    }
}
