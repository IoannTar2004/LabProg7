import client.modules.MaxValueComparison;
import org.example.collections.Dragon;
import org.example.collections.DragonFields;
import org.example.tools.Checks;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class ChecksTest {

    @Test
    void fileCheckerTest() throws FileNotFoundException {
        var checks = new Checks("xml");
        checks.fileChecker();
    }

    @Test
    void MaxValueTest() {
        MaxValueComparison.compare(new Dragon(), DragonFields.AGE, 45);
    }
}
