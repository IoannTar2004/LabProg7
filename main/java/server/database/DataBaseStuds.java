package server.database;

import org.example.collections.*;
import server.manager.ObjectsCollectionManager;
import server.manager.ObjectsManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DataBaseStuds extends DataBaseInitialization {

    public DataBaseStuds() {
        super("jdbc:postgresql://localhost:5432/studs",
                "s335973", "RkJlHCVb6Ywpfcol");
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

    public void removeFirst(String login) throws NullPointerException {
        try {
            Dragon dragon = new ObjectsCollectionManager().getAll().stream().
                    filter(dragon1 -> dragon1.getUserLogin().equals(login)).findFirst().orElse(null);
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM dragons " +
                    "WHERE id = ?");
            statement.setLong(1, dragon.getId());
            statement.execute();
            new ObjectsManager().remove(dragon);
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void removeById(String login, long id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM dragons " +
                    "WHERE id = ? and user_login = ?");
            statement.setLong(1, id);
            statement.setString(2, login);
            statement.execute();
        } catch (SQLException e) {e.printStackTrace();}
    }

    public void update(Object... attributes) {
        StringBuilder request = new StringBuilder("UPDATE dragons SET ");
        int len = 0;
        for (int j = 0; j < attributes.length; j++)  {
            if (attributes[j] != null) {len++;}
        }
        for (DragonFields fields: DragonFields.values()) {
            int i = fields.ordinal() + 1;
            if (attributes[i] != null) {
                request.append(fields.getField()).append("=");
                switch (fields) {
                    case NAME -> request.append("'").append(attributes[i]).append("'");
                    case COORDINATES -> request.append("'").append(attributes[i].toString()).append("'");
                    case AGE ->  request.append(attributes[i]);
                    case COLOR -> request.append("'").append(((Color) attributes[i]).getColor()).append("'");
                    case TYPE -> request.append("'").append(((DragonType) attributes[i]).getType()).append("'");
                    case CHARACTER -> request.append("'").append(((DragonCharacter) attributes[i]).getCharacter()).append("'");
                    case CAVE -> request.append(((DragonCave) attributes[i]).getDepth());
                }
                if (i < (len - 1)) {
                    request.append(", ");
                }
            }
        }
        request.append(" where id=").append(attributes[0]);
        try {
            getConnection().createStatement().execute(String.valueOf(request));
        } catch (SQLException e) {e.printStackTrace();}
    }
}
