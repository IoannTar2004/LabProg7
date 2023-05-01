package client;

import client.modules.ProgramStart;
import org.example.tools.InitializationDate;

public class Main {
    public static void main(String[] args) {
        InitializationDate.getDate();
        ProgramStart.start();
    }
}