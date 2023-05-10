package server.commands;

import org.example.tools.InitializationDate;
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
    public ServerSender execute(String mode, String[] command, Object... args) {
        ObjectsManager objectsManager = new ObjectsManager();
        DataBaseStuds studs = new DataBaseStuds();
        try {
            PreparedStatement statement = studs.getConnection().prepareStatement("SELECT COUNT(*) FROM dragons WHERE " +
                    "user_login = ?");
            statement.setString(1, (String) args[0]);
            ResultSet set = statement.executeQuery();
            set.next();
            return new ServerSender(List.of("Тип коллекции: ArrayDeque;",
                    "Дата инициализации: " + InitializationDate.getDate() + ";",
                    "Всего драконов в базе: " + objectsManager.fullLength() + ";",
                    "Ваших драконов: " + set.getInt(1) + ".\n"));
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }
}
