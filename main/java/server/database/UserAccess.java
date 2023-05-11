package server.database;

import server.commands.Command;
import server.modules.ServerSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserAccess implements Command {

    @Override
    public ServerSender<Boolean> execute(String mode, String[] command, String login, Object... args) {
        if(mode.equals("newUser")) {
            return newUser((String) args[0], (String) args[1]);
        } else if (mode.equals("existedUser")) {
            return existedUser(login, (String) args[0]);
        } else {
            return new ServerSender<>(List.of("Такой команды нет!"));
        }
    }

    private static ServerSender<Boolean> newUser(String login, String password) {
        DataBaseStuds studs = new DataBaseStuds();
        try {
            PreparedStatement statement = studs.getConnection().prepareStatement("INSERT INTO users VALUES (?,?)");
            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();
            return new ServerSender<>(List.of("Вы успешно зарегистрированы"), true);
        } catch (SQLException e) {
            return new ServerSender<>(List.of("Пользователь с таким логином уже существует! Попробуйте другой."), false);
        }
    }

    private static ServerSender<Boolean> existedUser(String login, String password) {
        DataBaseStuds studs = new DataBaseStuds();
        try {
            ResultSet set = studs.getConnection().createStatement().executeQuery("SELECT * FROM users");
            while (set.next()) {
                if (set.getString(1).equals(login) && set.getString(2).equals(password)) {
                    return new ServerSender<>(List.of("Вы успешно вошли!"), true);
                }
            }
            return new ServerSender<>(List.of("Не удалось войти. Проверьте логин и пароль."), false);
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }
}
