package server.commands;

import org.example.tools.OutputText;
import server.database.DataBaseStuds;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Clears collection.
 */
public class ClearCommand implements Command {
    /**
     * Clears collection.
     */
    @Override
    public ServerSender execute(String mode, String[] command, Object... args) {
        DataBaseStuds studs = new DataBaseStuds();
        String login = (String) args[0];
        try {
            PreparedStatement statement = studs.getConnection().prepareStatement("DELETE FROM dragons WHERE user_login=?");
            statement.setString(1, login);
            statement.execute();
            new ObjectsManager().clear(login);
            return new ServerSender(List.of(OutputText.result("Cleared")));
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }
}
