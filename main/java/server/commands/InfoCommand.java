package server.commands;

import server.database.DataBaseStuds;
import server.manager.ObjectsManager;
import server.modules.ServerSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Prints information about collection.
 */
public class InfoCommand implements Command {
    /**
     * Prints information about collection.
     */
    @Override
    public ServerSender execute(String mode, String[] command, String login, Object... args) {
        ObjectsManager objectsManager = new ObjectsManager();
        DataBaseStuds studs = new DataBaseStuds();
        try {
            PreparedStatement statement = studs.getConnection().prepareStatement("SELECT COUNT(*) FROM dragons WHERE " +
                    "user_login = ?");
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            set.next();
            return new ServerSender(List.of("Всего драконов в базе: " + objectsManager.fullLength() + ";",
                    "Ваших драконов: " + objectsManager.ownerLength(login) + ".\n"));
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }
}
