package server;

import server.multithreading.Consumer;
import server.multithreading.Producer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExchanger {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(30094)) {
            serverSocket.setReuseAddress(true);
            ExecutorService producers = Executors.newFixedThreadPool(10);
            //ExecutorService consumers = Executors.newCachedThreadPool(2);
            producers.submit(new Consumer());
            while (true) {
                Socket socket = serverSocket.accept();
                producers.submit(new Producer(socket));
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
