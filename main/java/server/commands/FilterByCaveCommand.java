package server.commands;

import org.example.collections.Dragon;
import org.example.collections.DragonCave;
import org.example.tools.Checks;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.LinkedList;
import java.util.List;

/**
 * Prints objects if they have a same cave depth
 */
public class FilterByCaveCommand implements Command {
    /**
     * Prints objects if they have a same cave depth.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        boolean check = false;
        List<String> dragonsList = new LinkedList<>();
        try {
            Checks checks = new Checks(command[1]);
            DragonCave cave1 = checks.caveChecker();
            ObjectsManager objectsManager = new ObjectsManager();
            ObjectsCollectionManager getters = new ObjectsCollectionManager();

            if (cave1 != null) {
                for (int i = 0; i < objectsManager.length(); i++) {
                    Dragon dragon = getters.getDragonByIndex(i);
                    if (getters.getCave(dragon) == cave1.getDepth()) {
                        dragonsList.add(dragon.toString());
                        check = true;
                    }
                }
                if (!check) {
                    return new ServerSender(List.of(OutputText.error("ObjectsNotFound"))) ;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ServerSender(List.of(OutputText.error("NoCaveArgument"))) ;
        }
        return new ServerSender(dragonsList);
    }
}
