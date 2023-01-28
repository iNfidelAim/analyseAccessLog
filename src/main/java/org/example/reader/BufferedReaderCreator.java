package org.example.reader;

import java.io.BufferedReader;

public class BufferedReaderCreator  implements Reader<BufferedReader> {
    private String fileName;
    private Reader<BufferedReader> reader;

    public BufferedReaderCreator() {
        this("");
    }

    public BufferedReaderCreator(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public BufferedReader getReader() {
        return getBufferedReader().getReader();
    }

    private Reader<BufferedReader> getBufferedReader() {
        if (reader != null) {
            return reader;
        }

        if (!fileName.isEmpty()) {
            reader = new FileBufferedReader(fileName);
        } else {
            reader = new InputStreamBufferedReader();
        }

        return reader;
    }
}
