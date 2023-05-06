package client.enter;

import client.modules.Processing;

import java.io.Serializable;

public class Registration implements Serializable {
    private String login;
    private String password;

    public void register(boolean register, Connection connection) {
        this.setLogin();
        this.setPassword();
        if (register) {
            String reply = connection.exchange()
        }
    }

    public void setLogin() {
        Processing processing = new Processing();
        System.out.println("Введите логин");
        String login;
        do {
            login = processing.scanner();
            if (login.equals("")) {
                System.out.println("Логин не может быть пустым!"); //TODO text
            } else if (login.length() > 32) {
                System.out.println("Превышена максимальная длина логина (32)!");
            }
        } while (login.equals("") || login.length() > 32);
        this.login = login;
    }

    public void setPassword() {
        System.out.println("Введите пароль");
        Processing processing = new Processing();
        String password;
        do {
            password = processing.scanner();
            if (password.equals("")) {
                System.out.println("Пароль не может быть пустым!"); //TODO text
            } else if (password.length() > 32) {
                System.out.println("Превышена максимальная длина пароля (32)!");
            }
        } while (password.equals("") || password.length() > 32);
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
