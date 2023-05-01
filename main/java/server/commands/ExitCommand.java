package server.commands;

import org.example.tools.OutputText;
import server.modules.ServerSender;
import server.tools.XMLWriteParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Saves collection to entered xml file.
 */
public class ExitCommand implements Command {
    /**
     * Saves collection to entered xml file.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("objects.xml"));

            writer.write(XMLWriteParser.parse());
            writer.flush();
            writer.close();
            return new ServerSender(List.of(OutputText.result("Saved")), new Object[]{"exit"});
        } catch (IOException e) {
            return new ServerSender(List.of(OutputText.error("FileNotFound")), new Object[]{"exit"});
        }
    }
}
