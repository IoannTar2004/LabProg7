package server.commands;

import org.example.collections.Dragon;
import org.example.tools.Checks;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.List;

/**
 * Count amount of objects which have greater age than entered.
 */
public class CountGreaterCommand implements Command {
    /**
     * Count amount of objects which have greater age than entered.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        try {
            ObjectsManager objectsManager = new ObjectsManager();
            ObjectsCollectionManager getters = new ObjectsCollectionManager();
            Checks checks = new Checks(command[1]);
            Integer age1 = checks.ageChecker();

            if (age1 != null) {
                int count = 0;
                for (int i = 0; i < objectsManager.length(); i++) {
                    Dragon dragon = getters.getDragonByIndex(i);
                    if (getters.getAge(dragon) > age1) {
                        count++;
                    }
                }
                return new ServerSender(List.of(String.valueOf(count)));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ServerSender(List.of(OutputText.error("NoAgeArgument")));
        }
        return null;
    }
}
