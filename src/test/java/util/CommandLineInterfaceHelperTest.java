package util;

import org.example.util.CommandLineInterfaceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineInterfaceHelperTest {
    private static final int TIME_DEFAULT = 30;
    private static final float ACCESSIBILITY_DEFAULT = 99.9F;

    private static final String TIME_OPT = "-t";
    private static final String ACCESSIBILITY_OPT = "-a";

    private static String[] args;

    @BeforeEach
    void init() {
        args = new String[]{TIME_OPT, String.valueOf(TIME_DEFAULT), ACCESSIBILITY_OPT, String.valueOf(ACCESSIBILITY_DEFAULT)};
        System.out.println("Arrays.asList(args) = " + Arrays.asList(args));
        CommandLineInterfaceHelper.parseArgs(args);
    }

    @Test
    void parseArgs() {
        assertNotNull(CommandLineInterfaceHelper.parseArgs(args));
        assertDoesNotThrow(() -> CommandLineInterfaceHelper.parseArgs(args));
    }

    @Test
    void getTimeValue() {
        assertEquals(CommandLineInterfaceHelper.getTimeValue(), TIME_DEFAULT);
    }

    @Test
    void getAccessibilityValue() {
        assertEquals(CommandLineInterfaceHelper.getAccessibilityValue(), ACCESSIBILITY_DEFAULT);
    }
}