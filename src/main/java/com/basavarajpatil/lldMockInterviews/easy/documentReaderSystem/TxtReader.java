package com.basavarajpatil.lldMockInterviews.easy.documentReaderSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtReader implements DocumentReader{
    private TxtReader(){}

    private static class TxtReaderHolder{
        private static final TxtReader INSTANCE = new TxtReader();
    }

    public static TxtReader getInstance(){
        return TxtReaderHolder.INSTANCE;
    }

    @Override
    public void read(String filePath) {
        System.out.println("Reading TXT file: " + filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Contents:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read TXT file: " + e.getMessage());
        }
    }

    @Override
    public void readInChunks(String filePath, int chunkSize) {
        System.out.println("Reading TXT file in chunks (chunk size = " + chunkSize + " chars): " + filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            char[] buffer = new char[chunkSize];
            int charsRead;
            while ((charsRead = br.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, charsRead));
            }
            System.out.println(); // newline after all chunks printed
        } catch (IOException e) {
            System.out.println("Failed to read TXT file in chunks: " + e.getMessage());
        }
    }
}
