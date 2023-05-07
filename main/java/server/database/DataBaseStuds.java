package server.database;

import org.example.collections.Dragon;
import server.manager.ObjectsCollectionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseStuds extends DataBaseInitialization {

    public DataBaseStuds() {
        super("jdbc:postgresql://localhost:2004/test",
                "postgres", "SPbass1470O");
    }

    public void insert(Dragon dragon) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " +
                    "dragons VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setLong(1, dragon.getId());
            statement.setString(2, dragon.getUserLogin());
            statement.setString(3, dragon.getName());
            statement.setString(4, dragon.getCoordinates());
            statement.setInt(5, dragon.getAge());
            statement.setString(6, dragon.getColor());
            statement.setString(7, dragon.getType());
            statement.setString(8, dragon.getCharacter());
            statement.setDouble(9, dragon.getCave());
            statement.setTimestamp(10, dragon.getCreationDate());
            statement.execute();
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void removeFirst() {
        try {
            Dragon dragon = new ObjectsCollectionManager().getDragonByIndex(0);
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM dragons" +
                    "WHERE id = ?");
            statement.setLong(1, dragon.getId());
        } catch (SQLException e) {e.printStackTrace();}
    }
}
