package client.enter;

import client.modules.Processing;
import client.modules.Validation;
import org.example.tools.OutputText;

import java.io.IOException;

public class ProgramStart {
    /**
     * This method runs at the beginning. It explains basic things of this program and requests initial xml file.
     */
    public static void start() {
        Connection connection = new Connection();
        Validation validation = new Validation();
        Registration registration = new Registration();

        connection.waitingForConnection();

        System.out.print("Данная программа хранит все объекты авторизованных пользователей в базе данных.\n" +
                "Вы уже зарегистрированы? "); //TODO text
        System.out.println(OutputText.startInformation("yes_no"));

        registration.register(validation.yesNoInput(), connection);

        System.out.println(OutputText.startInformation("ProgramReady"));
        new Processing().commandScan(connection, registration);
        try {
            connection.getSocket().close();
        } catch (IOException e) {e.printStackTrace();}

    }
}
