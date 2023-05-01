package server.modules;

import org.apache.commons.lang3.SerializationUtils;
import org.example.transmission.DataToServer;
import server.commands.*;
import server.commands.Command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerReader {
    private static Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("add", new AddCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("help", new HelpCommand());
        commands.put("clear", new ClearCommand());
        commands.put("head", new HeadCommand());
        commands.put("info", new InfoCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("remove_first", new RemoveFirstCommand());
        commands.put("exit", new ExitCommand());
        commands.put("show", new ShowCommand());

        commands.put("count_greater_than_age", new CountGreaterCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("filter_by_cave", new FilterByCaveCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("update", new UpdateIdCommand());
    }

    private Command command;
    private String[] commandString;
    private String mode;
    private Object[] objects;

    public ServerReader(String[] commandString) {
        this.command = commands.get(commandString[0]);
    }

    public ServerReader() {}

    public boolean read(SelectionKey key) {
        ByteBuffer buffer = ByteBuffer.allocate(100000);
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            socketChannel.read(buffer);
            DataToServer dataToServer = SerializationUtils.deserialize(buffer.array());

            command = commands.get(dataToServer.getCommandString()[0]);
            commandString = dataToServer.getCommandString();
            mode = dataToServer.getMode();
            objects = dataToServer.getObjects();

            System.out.println(this);
            socketChannel.configureBlocking(false);
            socketChannel.register(key.selector(), SelectionKey.OP_WRITE);

            return true;
        } catch (IOException e) {
            try {
                socketChannel.close();
            } catch (NullPointerException | IOException u) {u.printStackTrace();}
            return false;
        }
    }

    public Command getCommand() {
        return command;
    }

    public static Command getCommand(String commandString) {
        try {
            return commands.get(commandString);
        } catch (NullPointerException ignored) {} //Возникает, когда команда не найдена. Игнорирую.
        return null;
    }

    public String[] getCommandString() {
        return commandString;
    }

    public String getMode() {
        return mode;
    }

    public Object[] getObjects() {
        return objects;
    }

    @Override
    public String toString() {
        return "ServerReader{" +
                "command=" + command +
                ", commandString=" + Arrays.toString(commandString) +
                ", mode='" + mode + '\'' +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
