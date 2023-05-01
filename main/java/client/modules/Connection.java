package client.modules;

import org.apache.commons.lang3.SerializationUtils;
import org.example.tools.OutputText;
import org.example.transmission.DataToClient;
import org.example.transmission.DataToServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;

public class Connection {
    private String host;
    private int port;
    private SocketChannel socketChannel;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
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
    public SocketChannel waitingForConnection() throws UnresolvedAddressException {
        while (true) {
            try {
                socketChannel = SocketChannel.open(new InetSocketAddress(host, port));;
                return socketChannel;
            } catch (IOException ignored) {}
        }
    }

    public Object[] exchange(String[] input, String mode, Object[] objects) throws IOException{
        ByteBuffer buffer = ByteBuffer.allocate(100000);
        DataToServer sender = new DataToServer(input, mode, objects);

        byte[] bytes = SerializationUtils.serialize(sender);
        socketChannel.write(ByteBuffer.wrap(bytes));
        socketChannel.read(buffer);
        DataToClient result = SerializationUtils.deserialize(buffer.array());
        try {
            result.getResult().forEach(System.out::println);
        } catch (Exception ignored) {}

        return result.getArguments();

    }
}
