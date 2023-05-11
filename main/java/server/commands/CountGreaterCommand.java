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
    public ServerSender execute(String mode, String[] command, String login, Object... args) {
        try {
            Checks checks = new Checks(command[1]);
            Integer age1 = checks.ageChecker();
            if (age1 != null) {
                long count = new ObjectsCollectionManager().getAll().stream().
                        filter(dragon1 -> dragon1.getUserLogin().equals(login) && dragon1.getAge() > age1).count();
                return new ServerSender(List.of(String.valueOf(count)));
            } else {
                return new ServerSender(List.of("Возраст - целое положительное число"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ServerSender(List.of(OutputText.error("NoAgeArgument")));
        }
    }
}
