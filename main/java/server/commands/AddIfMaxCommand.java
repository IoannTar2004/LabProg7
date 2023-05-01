package server.commands;

import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.OutputText;
import server.manager.ObjectsManager;
import server.modules.ServerSender;
import server.tools.MaxField;

import java.util.List;
import java.util.Objects;

/**
 * Add object to collection if max value of one field is less than entered value.
 */
public class AddIfMaxCommand implements Command {

    /**
     * Add object to collection if max value of one field is less than entered value.
     *
     * @param mode
     * @param command
     * @param args
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        if (Objects.equals(mode, "server1")) {
            if (args[0] == null) {
                return new ServerSender(List.of(OutputText.error("FieldIncorrect")), new Object[]{null});
            }
            DragonFields fields = (DragonFields) args[0];
            Dragon dragon = MaxField.max(fields);
            if (!MaxField.existence(fields, dragon)) {
                return new ServerSender(List.of(OutputText.maxValues("Max_" + fields.getField())), new Object[]{null});
            }
            return new ServerSender(new Object[]{dragon});
        } else if (Objects.equals(mode, "server2")) {
            new ObjectsManager().add(args);
            return new ServerSender(List.of(OutputText.result("Added")));
        } else {
           return new ServerSender(new Object[]{"addIfMaxDragon"});
        }
    }
}
