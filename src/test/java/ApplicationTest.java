

import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class ApplicationTest  {
    @Test
    void runAppWithoutData() {
        String[] args = new String[]{"-t", "42", "-a", "92.0"};
        assertDoesNotThrow(() -> Main.main(args));
    }

    @Test
    void runAppWithDataFromFile() {
        String[] args = new String[]{"-t", "42", "-a", "94.0", "-f", "access.log"};
        assertDoesNotThrow(() -> Main.main(args));
    }
}



