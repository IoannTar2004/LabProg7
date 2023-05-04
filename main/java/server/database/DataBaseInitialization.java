package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
}
