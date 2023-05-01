package server.commands;

import org.example.collections.Dragon;
import org.example.tools.IdChecker;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.List;
import java.util.Objects;

/**
 * Removes object by its ID.
 */
public class RemoveByIdCommand implements Command {
    /**
     * Removes object by its ID.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        try {
            ObjectsManager objectsManager = new ObjectsManager();
            String output = IdChecker.check(new ObjectsCollectionManager().getAll(), command[1]);
            if (Objects.equals(output, "Existed")) {
                Dragon dragon = new ObjectsCollectionManager().getDragonById(Long.parseLong(command[1]));
                objectsManager.remove(dragon);
                return new ServerSender(List.of(OutputText.result("Removed")));
            } else {
                return new ServerSender(List.of(output));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ServerSender(List.of(OutputText.error("NoIdArgument")));
        }
    }
}
