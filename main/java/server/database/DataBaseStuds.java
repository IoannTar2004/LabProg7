package server.database;

import org.example.collections.Dragon;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseStuds extends DataBaseInitialization {

    public DataBaseStuds() {
        super("jdbc:postgresql://localhost:2004/test",
                "postgres", "SPbass1470O");
    }

    public void insert(Dragon dragon) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " +
                    "dragons VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setLong(1, dragon.getId());
            statement.setString(2, "ivan");
            statement.setString(3, dragon.getName());
            statement.setString(4, dragon.getCoordinates());
            statement.setInt(5, dragon.getAge());
            statement.setString(6, dragon.getColor());
            statement.setString(7, dragon.getType());
            statement.setString(8, dragon.getCharacter());
            statement.setDouble(9, dragon.getCave());
            statement.setTimestamp(10, dragon.getCreationDate());

            statement.execute();
        } catch (SQLException ignored) {ignored.printStackTrace();} //никогда не выбросится
    }
}
