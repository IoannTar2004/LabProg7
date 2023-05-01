package server.modules;

import server.commands.Command;
import java.util.List;
import java.util.Objects;

public class ServerInvoker {
    /**
     * This method receives entered or read from script command and splits by spaces.
     * It works while command is not "exit".
     */
    public static ServerSender invoke(Command command, String mode, String[] input, Object... args) {
        try {
            return command.execute(mode, input, args);
        } catch (NullPointerException e) {
            if (Objects.equals(mode, "user")) {
                return new ServerSender(List.of("Такой команды нет!"));
            }
        }
        return new ServerSender(new Object[]{""});
    }
}
