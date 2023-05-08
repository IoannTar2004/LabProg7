package client.modules;

import client.enter.Connection;
import client.enter.Registration;

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
    public void commandScan(Connection connection, Registration registration) {
        String input;
        do {
            input = scanner();
            if (input.length() > 0) {
                if (input.matches("execute_script.*")) {
                    new Validation().scriptParse(connection, input.split("\\s+"));
                } else {
                    try {
                        Object[] arguments = connection.<String, String>exchange(input.split("\\s+"), "user",
                                registration.getLogin());

                        Class<Validation> valid = Validation.class;
                        Method method = valid.getDeclaredMethod((String) arguments[0], Connection.class, Registration.class,Object[].class);
                        method.invoke(new Validation(), connection, registration, arguments);
                    }  catch (Exception ignored) {}
                    //Некоторые команды отправляются без аргументов, и из-за этого вылетают эти исключения. Игнорирую.
                }
            }
        } while (!input.equals("exit"));
    }
}
