package server.multithreading;

import server.modules.ServerReader;
import java.net.Socket;

public class Producer implements Runnable {
    private final Socket socket;

    public Producer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ServerReader reader = new ServerReader();
        while (reader.read(socket)) {
            try {
                synchronized (TaskQueue.getQueue()) {
                    TaskQueue.add(reader);
                    TaskQueue.getQueue().notify();
                }
            } catch (IllegalStateException e) {
                synchronized (TaskQueue.getQueue()) {
                    try {
                        TaskQueue.getQueue().wait();
                    } catch (InterruptedException ignored) {}
                }
            }
        }
    }
}
