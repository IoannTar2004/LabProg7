import org.example.collections.Dragon;
import org.junit.jupiter.api.Test;
import server.commands.AddCommand;
import server.modules.ServerReader;

class CommandsTest {
    @Test
    void showTest() {
        new AddCommand().execute("user", new String[]{"add"}, new Dragon());
    }

    @Test
    void unknownCommand(){
        ServerReader reader = new ServerReader(new String[]{"ejndj"});
        assert
    }

}
