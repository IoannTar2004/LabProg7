package org.example.transmission;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * General class for connecting client and server. It is used to send reply from server to client.
 */
public class DataToClient<T> implements Serializable {
    private List<String> result;
    private T[] arguments;

    @SafeVarargs
    public DataToClient(List<String> result, T... arguments) {
        this.result = result;
        this.arguments = arguments;
    }

    public List<String> getResult() {
        return result;
    }

    public T[] getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "DataToClient{" +
                "result=" + result +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}
