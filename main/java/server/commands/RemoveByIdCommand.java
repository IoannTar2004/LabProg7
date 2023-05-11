package server.commands;

import org.example.collections.Dragon;
import org.example.tools.IdChecker;
import org.example.tools.OutputText;
import server.database.DataBaseStuds;
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
    public ServerSender execute(String mode, String[] command, String login, Object... args) {
        try {
            ObjectsManager objectsManager = new ObjectsManager();
            DataBaseStuds studs = new DataBaseStuds();
            long id = Long.parseLong(command[1]);

            Dragon dragon = new ObjectsCollectionManager().getDragonById(login,id);
            studs.removeById(login, id);
            objectsManager.remove(dragon);
            return new ServerSender(List.of(OutputText.result("Removed")));

        } catch (ArrayIndexOutOfBoundsException e) {
            return new ServerSender(List.of(OutputText.error("NoIdArgument")));
        } catch (NumberFormatException e) {
            return new ServerSender(List.of("id - целое положительное число!"));
        } catch (NullPointerException e) {
            return new ServerSender(List.of("Объекта с таким id в вашей коллекции не существует"));
        }
    }
}
