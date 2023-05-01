package server.modules;

import org.apache.commons.lang3.SerializationUtils;
import org.example.transmission.DataToClient;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ServerSender implements Serializable {
    private List<String> result;
    private Object[] arguments = {};

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

    public void send(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            DataToClient dataToClient = new DataToClient(result, arguments);
            byte[] bytes = SerializationUtils.serialize(dataToClient);
            socketChannel.write(ByteBuffer.wrap(bytes));

           if (Arrays.binarySearch(arguments, "exit") >= 0) {
                try (FileChannel fileChannel = FileChannel.open(Paths.get("objects.xml"))) {
                    fileChannel.transferTo(0, fileChannel.size(), socketChannel);
                    socketChannel.close();
                }
            }
        } catch (IOException ignored) {} //возникает, когда клиент отключается
        try {
            socketChannel.configureBlocking(false);
            socketChannel.register(key.selector(), SelectionKey.OP_READ);
        } catch (IOException e) {e.printStackTrace();}
    }

    @Override
    public String toString() {
        return "ServerSender{" +
                "result=" + result +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}