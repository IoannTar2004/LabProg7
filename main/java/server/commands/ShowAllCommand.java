package server.commands;

import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsElements;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.ArrayList;
import java.util.List;

public class ShowAllCommand implements Command {

    @Override
    public ServerSender execute(String mode, String[] command, String login, Object... args) {
        ObjectsManager objectsManager = new ObjectsManager();
        ObjectsElements objectsElements = new ObjectsElements();
        ObjectsCollectionManager getters = new ObjectsCollectionManager();
        List<String> list = new ArrayList<>();
        if (objectsManager.fullLength() > 0) {
            for (int i = 0; i < objectsManager.fullLength(); i++) {
                list.add(objectsElements.element(getters.getDragonByIndex(i), command));
            }
        }
        return list.size() > 0 ? new ServerSender<>(list) : new ServerSender<>(List.of(OutputText.result("Empty")));
    }
}
