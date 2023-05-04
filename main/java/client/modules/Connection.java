package client.modules;

import org.apache.commons.lang3.SerializationUtils;
import org.example.tools.OutputText;
import org.example.transmission.DataToClient;
import org.example.transmission.DataToServer;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;

public class Connection {
    private String host;
    private int port;
    private Socket socket;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
    public SocketChannel getSocketChannel() {return null;}

    public Socket getSocket() {
        return socket;
    }

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public Connection() {}

    /**
     * Method processes input of server host and port
     * @return socketChannel
     */
    public void connectionToServer() {
        Processing processing = new Processing();
        System.out.println(OutputText.startInformation("ServerInfo"));
        do {
            String host = processing.scanner();
            this.host = host;
            System.out.println(OutputText.startInformation("EnterPort"));
            do {
                try {
                    int port = Integer.parseInt(processing.scanner());
                    this.port = port;
                    waitingForConnection();
                    return;
                } catch (UnresolvedAddressException e) {
                    System.out.println(OutputText.serverError("UnknownHost"));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(OutputText.serverError("PortIsInt"));
                }
                catch (Exception ignored) {}
            } while (true);
        } while (true);
    }

    /**
     * Run endless tries to connect to server
     * @return open and return socket channel
     * @throws UnresolvedAddressException when host is unknown
     */
    public void waitingForConnection() throws UnresolvedAddressException {
        while (true) {
            try {
                socket = new Socket(host, port);
                return;
            } catch (IOException ignored) {}
        }
    }

    public Object[] exchange(String[] input, String mode, Object[] objects) throws IOException{
        DataToServer sender = new DataToServer(input, mode, objects);

        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(sender);

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            DataToClient result = (DataToClient) in.readObject();
            try {
                result.getResult().forEach(System.out::println);
            } catch (Exception ignored) {} //Пустой результат
            return result.getArguments();

        } catch (ClassNotFoundException e) {e.printStackTrace();}
        return null;
    }
}