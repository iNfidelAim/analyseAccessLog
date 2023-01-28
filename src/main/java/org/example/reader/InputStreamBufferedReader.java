package org.example.reader;

import java.io.BufferedReader;

public class InputStreamBufferedReader implements Reader<BufferedReader> {
    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new java.io.InputStreamReader(System.in));
    }
}
