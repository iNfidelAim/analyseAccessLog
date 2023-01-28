package org.example.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ErrorHandler {

        private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

        public static void printStackTrace(StackTraceElement[] elements) {
        logger.error(
                "ERRORS:\n{}",
                Arrays.asList(elements)
                        .stream()
                        .map(stackTraceElement -> stackTraceElement + "\n")
                        .collect(Collectors.toList())
        );
    }
}
