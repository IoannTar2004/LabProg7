package server.modules;

import org.example.transmission.DataToClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ServerSender<T> implements Runnable {
    private List<String> result;
    private T[] arguments;
    private Socket socket;

    public ServerSender(List<String> result) {
        this.result = result;
    }

    public ServerSender(T[] arguments) {
        this.arguments = arguments;
    }

    public ServerSender(List<String> result, T[] arguments) {
        this.result = result;
        this.arguments = arguments;
    }

    public List<String> getResult() {
        return result;
    }

    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public void run() {
        try {
            DataToClient<T> dataToClient = new DataToClient<>(result, arguments);
            ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
            stream.writeObject(dataToClient);

        } catch (IOException ignored) {} //возникает, когда клиент отключается

    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "ServerSender{" +
                "result=" + result +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}