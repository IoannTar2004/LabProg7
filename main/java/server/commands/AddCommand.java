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
        if (Objects.equals(mode, "script")) {
            for (DragonFields fields: DragonFields.values()) {
                args[fields.ordinal()] = new DragonOptions().dragonProcessing(fields, (String) args[fields.ordinal()]);
            }
            new ObjectsManager().insert(args);
            return new ServerSender(List.of(OutputText.result("Added")));

        } else if (Objects.equals(mode, "collection")) {
            new ObjectsManager().insert((Dragon) args[0]);
            return new ServerSender(List.of(OutputText.result("Added")));

        } else {
            return new ServerSender(new Object[]{"addDragon"});
        }
    }
}