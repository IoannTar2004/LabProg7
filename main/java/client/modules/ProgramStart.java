package client.modules;

import client.readers.XMLReader;
import org.example.collections.Dragon;
import org.example.tools.Checks;
import org.example.tools.FileManager;
import org.example.tools.OutputText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ProgramStart {
    /**
     * This method runs at the beginning. It explains basic things of this program and requests initial xml file.
     */
    public static void start() {
        Connection connection = new Connection();
        Processing processing = new Processing();

        connection.connectionToServer();

        System.out.println(OutputText.startInformation("ProgramReady"));
        processing.commandScan(connection);
        try {
            connection.getSocket().close();
        } catch (IOException e) {e.printStackTrace();}

    }
}
