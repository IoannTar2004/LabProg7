package server.modules;

import org.example.transmission.DataToClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ServerSender implements Runnable {
    private List<String> result;
    private Object[] arguments = {};
    private Socket socket;

    public ServerSender(List<String> result) {
        this.result = result;
    }

    public ServerSender(Object[] arguments) {
        this.arguments = arguments;
    }

    public ServerSender(List<String> result, Object[] arguments) {
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
            DataToClient dataToClient = new DataToClient(result, arguments);
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