package org.example.reader;

import org.example.util.ErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileBufferedReader implements Reader<BufferedReader> {
    private static final String FILE_NOT_FOUND = "File not found!";
    private String fileName;

    public FileBufferedReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public BufferedReader getReader() {
        return getFileReader(fileName);
    }

    private BufferedReader getFileReader(String fileName) {
        Path filePath = getFilePathFromCurrentDir(fileName);
        boolean isFileExists = isFileExists(filePath);
        if (isFileExists) {
            try {
                return Files.newBufferedReader(filePath);
            } catch (IOException e) {
                ErrorHandler.printStackTrace(e.getStackTrace());
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException(FILE_NOT_FOUND);
        }
    }

    private Path getFilePathFromCurrentDir(String fileName) {
        return Paths.get(fileName);
    }

    private boolean isFileExists(Path path) {
        return Files.exists(path);
    }
}