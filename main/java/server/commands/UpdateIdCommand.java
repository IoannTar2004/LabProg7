package server.commands;

import org.example.collections.DragonFields;
import org.example.tools.DragonOptions;
import org.example.tools.OutputText;
import server.database.DataBaseStuds;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.util.List;
import java.util.Objects;

/**
 * Changes dragon's fields by its ID.
 */
public class UpdateIdCommand implements Command {
    /**
     * Changes dragon's fields by its ID.
     */
    @Override
    public ServerSender<String> execute(String mode, String[] command, String login, Object... args) {
         if (Objects.equals(mode, "script")) {
             for (DragonFields fields: DragonFields.values()) {
                 args[fields.ordinal()] = new DragonOptions().dragonProcessing(fields, (String) args[fields.ordinal()]);
             }
             //new ObjectsManager().replace(id, args);
             return new ServerSender<>(List.of(""));

         } else if (Objects.equals(mode, "collection")) {
             new DataBaseStuds().update(args);
             new ObjectsManager().replace(args);
             return new ServerSender<>(List.of(OutputText.result("DataChanged")));

         } else {
            try {
                new ObjectsCollectionManager().getDragonById(login, Long.parseLong(command[1]));
                return new ServerSender<>(new String[]{"updateDragon", command[1]});
            } catch (ArrayIndexOutOfBoundsException e) {
                return new ServerSender<>(List.of(OutputText.error("NoIdArgument")));
            } catch (NullPointerException e) {
                return new ServerSender<>(List.of(OutputText.error("DragonDoesNotExist")));
            } catch (NumberFormatException e) {
                return new ServerSender<>(List.of("id - целое положительное число!"));
            }
        }
    }

}
