package server.tools;

import org.example.collections.Dragon;
import server.database.DataBaseStuds;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * class for id generate
 */
public class IdGenerator {
    /**
     * generate 12-digit id. It is guaranteed that object will have unique id.
     * @return id
     */
    public static Long generate() {
        DataBaseStuds studs = new DataBaseStuds();
        try {
            Statement statement = studs.getConnection().createStatement();
            ResultSet set = statement.executeQuery("select nextval('dragon_id')");
            set.next();

            return set.getLong(1);
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }
}
