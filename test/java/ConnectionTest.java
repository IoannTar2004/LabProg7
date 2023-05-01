import client.modules.Connection;
import org.junit.jupiter.api.Test;

class ConnectionTest {
    @Test
    void connect() {
        new Connection("localhos", 30094).waitingForConnection();
    }
}
