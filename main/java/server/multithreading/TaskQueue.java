package server.multithreading;

import server.modules.ServerReader;

import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
    private static LinkedBlockingQueue<ServerReader> queue = new LinkedBlockingQueue(10);

    public static void add(ServerReader task) throws IllegalStateException {
        queue.add(task);
    }

    public static ServerReader poll() throws NullPointerException {
        return queue.poll();
    }

    public static LinkedBlockingQueue<ServerReader> getQueue() {
        return queue;
    }
}
