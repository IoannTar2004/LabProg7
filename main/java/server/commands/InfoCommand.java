package server.commands;

import org.example.tools.InitializationDate;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.List;

/**
 * Prints information about collection.
 */
public class InfoCommand implements Command {
    /**
     * Prints information about collection.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        ObjectsManager objectsManager = new ObjectsManager();
        return new ServerSender(List.of("Тип коллекции: ArrayDeque;",
                "Дата инициализации: " + InitializationDate.getDate() + ";",
                "Количество элементов: " + objectsManager.length() + ".\n"));
    }
}
