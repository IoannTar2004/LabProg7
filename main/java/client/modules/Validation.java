package client.modules;

import client.readers.ScriptReader;
import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.Checks;
import org.example.tools.DragonOptions;
import org.example.tools.FileManager;
import org.example.tools.OutputText;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;

public class Validation {

    /**
     * Triggers when user enters command "add" to terminal
     */
    public void addDragon(Connection connection, Object... data) throws IOException {
        Processing manager = new Processing();
        DragonOptions dragonOptions = new DragonOptions();
        Dragon dragon = new Dragon();
        for (DragonFields fields: DragonFields.values()) {
            Object element;
            System.out.println(OutputText.input(fields.getField() + "Input"));
            do {
                String input = manager.scanner();
                element = dragonOptions.dragonProcessing(fields, input);
                dragon = dragonOptions.dragonInput(dragon, fields, element);
            } while (element == null);
        }
        dragon.setUserId(1);
        connection.exchange(new String[]{"add"}, "collection", new Object[]{dragon});
    }

    /**
     * Triggers when user enters command "update" to terminal
     */
    public void updateDragon(Connection connection, Object... data) throws IOException {
        Processing manager = new Processing();
        Object[] args = new Object[7];
        for (DragonFields fields: DragonFields.values()) {
            args[fields.ordinal()] = null;
            System.out.println(OutputText.input(fields.getField() + "NewInput"));
            do {
                String input = manager.scanner();
                if (input.length() == 0) {
                    break;
                }
                args[fields.ordinal()] = new DragonOptions().dragonProcessing(fields, input);
            } while (args[fields.ordinal()] == null);
        }
        connection.exchange(new String[]{"update"},"collection", args);
    }

    /**
     * Triggers when user enters command "add_if_max" to terminal
     */
    public void addIfMaxDragon(Connection connection, Object... data) throws IOException {
        DragonFields fieldNum;
        Dragon dragon;
        Processing manager = new Processing();
        Object[] args = new Object[7];
        String input;

        System.out.println(OutputText.input("FieldInput"));
        do {
            input = manager.scanner();
            fieldNum = DragonFields.getFieldByNumber(input);
            dragon = (Dragon) connection.exchange(new String[]{"add_if_max"},"server1", new Object[]{fieldNum})[0];
        } while (fieldNum == null || dragon == null);

        for (DragonFields fields: DragonFields.values()) {
            Object element;
            System.out.println(OutputText.input(fields.getField() + "Input"));
            do {
                input = manager.scanner();
                element = new DragonOptions().dragonProcessing(fields, input);
                if (fields == fieldNum && element != null) {
                    if (MaxValueComparison.compare(dragon, fields, element)) {
                        args[fields.ordinal()] = element;
                        break;
                    } else {
                        element = null;
                    }
                }
            } while (element == null);
            args[fields.ordinal()] = element;
        }
        connection.exchange(new String[]{"add_if_max"},"server2", args);
    }
    /**
     * Triggers when user enters command "print_descending" to terminal
     */
    public void fieldSelection(Connection connection, Object... data) throws IOException {
        DragonFields fieldNum;
        Processing manager = new Processing();

        System.out.println(OutputText.input("DescendingInput"));
        do {
            String input = manager.scanner();
            fieldNum = DragonFields.getFieldByNumber(input);
        } while (fieldNum == null);
        connection.exchange(new String[]{"print_descending"},"collection", new Object[]{fieldNum});
    }

    /**
     * Method is used for answer the closed questions
     * @return true if input is 'y' (yes), else return false if input is 'n' (no)
     */
    public boolean yesNoInput() {
        Processing manager = new Processing();
        String data;
        do {
            data = manager.scanner();
            if(data.equals("y")) {
                return true;
            } else if(data.equals("n")) {
                return false;
            }
            System.out.println(OutputText.startInformation("yes_no"));
        } while (true);
    }

    /**
     * Triggers when user enters command "execute_script" to terminal
     */
    public void scriptParse(Connection connection, Object... data) {
        try {
            File file = new Checks((String) data[1]).fileChecker();
            List<String> commands;
            FileManager.addFileToStack(file);
            commands = ScriptReader.read(file);
            if (commands.size() > 0) {
                Object[] args = connection.exchange(new String[]{"execute_script"}, "script", new Object[]{commands});
                int i = Arrays.binarySearch(args, "exit");
                if(i >= 0) {
                    FileManager.clear();
                    exit(connection);
                }
            }
            FileManager.clear();
        } catch (ArrayIndexOutOfBoundsException e) {
           System.out.println(OutputText.error("NoFileArgument"));
        } catch (IOException e) {
            System.out.println(List.of(OutputText.error("FileNotFound")));
        }
    }

    /**
     * Triggers when user enters command "exit" to terminal
     */
    public void exit(Connection connection, Object... objects) {
        SocketChannel socketChannel = connection.getSocketChannel();
        String path = FileManager.getCurrentFile().getAbsolutePath();

        try (RandomAccessFile accessFile = new RandomAccessFile(path, "rw")) {
            FileChannel fileChannel = accessFile.getChannel();
            new FileWriter(path).close();
            fileChannel.transferFrom(socketChannel,0, 100000);

            socketChannel.close();
            fileChannel.close();
            System.exit(0);
        } catch (Exception ignored) {}
    }
}
