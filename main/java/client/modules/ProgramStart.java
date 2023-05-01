package client.modules;

import client.readers.XMLReader;
import org.example.collections.Dragon;
import org.example.tools.Checks;
import org.example.tools.FileManager;
import org.example.tools.OutputText;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ProgramStart {
    /**
     * This method runs at the beginning. It explains basic things of this program and requests initial xml file.
     */
    public static void start() {
        Connection connection = new Connection();
        Validation validation = new Validation();
        Processing processing = new Processing();

        connection.connectionToServer();

        try {
            System.out.println(OutputText.startInformation("CorrectXmlFile"));
            String data;
            if (validation.yesNoInput()) {
                System.out.println(OutputText.startInformation("Example"));
            }

            System.out.println(OutputText.startInformation("EnvVar"));
            File file = null;
            do {
                data = processing.scanner();
                try {
                    file = new Checks(data).fileChecker();
                } catch (FileNotFoundException e) {
                    System.out.println(OutputText.error("FileNotFound"));
                }
                if (file != null) {
                    List<Dragon> list = XMLReader.parse(file);
                    FileManager.setCurrentFile(file);
                    connection.exchange(new String[]{"add"}, "xml", new Object[]{list, file});
                }
            } while (file == null);

            System.out.println(OutputText.startInformation("ProgramReady"));
            processing.commandScan(connection);
            connection.getSocketChannel().close();

        } catch (Exception e) {e.printStackTrace();}
    }
}
