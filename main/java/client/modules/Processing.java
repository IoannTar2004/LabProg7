package client.modules;

import org.example.tools.OutputText;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * class for processing inputs
 */
public class Processing {

    /**
     * Scan entered strings, if user enters ctrl + d the program will be stopped.
     * @return entered string
     */
    public String scanner() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        return null;
    }

    /**
     * Method reads commands entered by user.
     */
    public void commandScan(Connection connection) {
        String input;
        do {
            input = scanner();
            if (input.length() > 0) {
                if (input.matches("execute_script.*")) {
                    new Validation().scriptParse(connection, input.split("\\s+"));
                } else {
                    try {
                        Object[] arguments = connection.exchange(input.split("\\s+"), "user",
                                null);

                        Class<Validation> valid = Validation.class;
                        Method method = valid.getDeclaredMethod((String) arguments[0], Connection.class, Object[].class);
                        method.invoke(new Validation(), connection, arguments);
                    } catch (IOException e) {
                        System.out.println(OutputText.serverError("ConnectionStop"));
                        connection.waitingForConnection();
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException
                            | ArrayIndexOutOfBoundsException ignored) {}
                    //Некоторые команды отправляются без аргументов, и из-за этого вылетают эти исключения. Игнорирую.
                }
            }
        } while (!input.equals("exit"));
    }
}
