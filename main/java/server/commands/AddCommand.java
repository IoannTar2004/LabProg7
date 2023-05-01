package server.commands;

import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.DragonOptions;
import org.example.tools.FileManager;
import org.example.tools.OutputText;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Add object to collection.
 */
public class AddCommand implements Command {

    /**
     * Add object to collection.
     *
     * @param mode
     * @param command
     * @param args
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        if (Objects.equals(mode, "xml")) {
            List<Dragon> list = new LinkedList<>((List<Dragon>) args[0]);
            for (Dragon dragon : list) {
                if (new ObjectsCollectionManager().getDragonById(dragon.getId()) == null) {
                    new ObjectsManager().add(dragon);
                }
            }
            FileManager.setCurrentFile((File) args[1]);
            return new ServerSender(new Object[]{""});

        } else if (Objects.equals(mode, "script")) {
            for (DragonFields fields: DragonFields.values()) {
                args[fields.ordinal()] = new DragonOptions().dragonProcessing(fields, (String) args[fields.ordinal()]);
            }
            new ObjectsManager().add(args);
            return new ServerSender(List.of(OutputText.result("Added")));

        } else if (Objects.equals(mode, "collection")) {
            new ObjectsManager().add(args);
            return new ServerSender(List.of(OutputText.result("Added")));

        } else {
            return new ServerSender(new Object[]{"addDragon"});
        }
    }
}
