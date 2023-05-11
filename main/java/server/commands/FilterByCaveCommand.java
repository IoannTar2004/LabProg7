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
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Prints objects if they have a same cave depth
 */
public class FilterByCaveCommand implements Command {
    /**
     * Prints objects if they have a same cave depth.
     */
    @Override
    public ServerSender execute(String mode, String[] command, String login, Object... args) {
        try {
            Checks checks = new Checks(command[1]);
            DragonCave cave1 = checks.caveChecker();

            if (cave1 != null) {
                List<String> filteredList = new ObjectsCollectionManager().getAll().stream().filter(dragon ->
                        Objects.equals(dragon.getCave(), cave1.getDepth()) && dragon.getUserLogin().equals(login)).
                map(Dragon::toString).toList();
                if (filteredList.size() == 0) {
                    return new ServerSender(List.of(OutputText.error("ObjectsNotFound"))) ;
                }
                return new ServerSender(filteredList);
            }
            return new ServerSender(List.of("Глубина пещеры - дробное число через точку"));
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ServerSender(List.of(OutputText.error("NoCaveArgument"))) ;
        }
    }
}
