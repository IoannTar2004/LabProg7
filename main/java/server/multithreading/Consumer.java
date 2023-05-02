package server.multithreading;

import server.modules.ServerInvoker;
import server.modules.ServerReader;
import server.modules.ServerSender;

public class Consumer implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (TaskQueue.getQueue()) {
                    ServerReader reader = TaskQueue.poll();

                    ServerSender serverSender = ServerInvoker.invoke(reader.getCommand(), reader.getMode(),
                            reader.getCommandString(), reader.getObjects());
                    serverSender.setSocket(reader.getSocket());
                    new Thread(serverSender).start();

                    TaskQueue.getQueue().notify();
                }
            } catch (NullPointerException e) {
                synchronized (TaskQueue.getQueue()) {
                    try {
                        TaskQueue.getQueue().wait();
                    } catch (InterruptedException ignored) {}
                }
            }
        }
    }
}
