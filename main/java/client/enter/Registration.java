package client.enter;

import client.modules.Processing;

public class Registration {
    private String login;
    private String password;

    public void setLogin() {
        Processing processing = new Processing();
        System.out.println("Введите логин");
        String login;
        do {
            login = processing.scanner();
            if (login == null) {
                System.out.println("Логин не может быть пустым!"); //TODO text
            } else if (login.length() > 32) {
                System.out.println("Превышена максимальная длина логина (32)!");
            }
        } while (login == null || login.length() > 32);
        this.login = login;
    }

    public void setPassword() {
        System.out.println("Введите пароль");
        Processing processing = new Processing();
        String password;
        do {
            password = processing.scanner();
            if (password == null) {
                System.out.println("Пароль не может быть пустым!"); //TODO text
            } else if (password.length() > 32) {
                System.out.println("Превышена максимальная длина пароля (32)!");
            }
        } while (password == null || password.length() > 32);
        this.password = password;
    }
}
