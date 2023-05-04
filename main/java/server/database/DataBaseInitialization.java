package server.database;

import org.example.collections.Dragon;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public abstract class DataBaseInitialization {
    Connection connection;

    private static Map<String, Connection> connections = new HashMap<>();

    public DataBaseInitialization(String url, String user, String password) {
        String key = url + "_" + user + "_" + password;
        if (connections.get(key) == null) {
            try {
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                connections.put(key, connection);
            } catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
        }
        this.connection = connections.get(key);
    }

    public Connection getConnection() {
        return connection;
    }

    public static void connect(String url, String user, String password) {
        String key = url + "_" + user + "_" + password;
        if (connections.get(key) == null) {
            try {
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                connections.put(key, connection);
                readFromDataBase(connection);
            } catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
        }
    }

    private static void readFromDataBase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM dragons");

        while (set.next()) {
            Dragon dragon = new Dragon();
            for (int i = 1; i < 10; i++) {
                //dragon.;
            }
        }
    }
}
