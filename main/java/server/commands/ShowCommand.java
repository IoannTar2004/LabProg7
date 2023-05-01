package server.commands;

import org.example.collections.Dragon;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsElements;
import server.manager.ObjectsManager;
import server.modules.ServerSender;
import server.tools.Sort;

import java.util.ArrayList;
import java.util.Collections;
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
    public ServerSender execute(String mode, String[] command, Object... args) {
        List<String> list = new LinkedList<>();
        ObjectsManager objectsManager = new ObjectsManager();
        ObjectsElements objectsElements = new ObjectsElements();
        ObjectsCollectionManager getters = new ObjectsCollectionManager();

        if (objectsManager.length() > 0) {
            for (int i = 0; i < objectsManager.length(); i++) {
                list.add(objectsElements.element(getters.getDragonByIndex(i), command));
            }
            return new ServerSender(list);
        } else {
            return new ServerSender(List.of(OutputText.result("Empty")));
        }
    }
}
