package com.basavarajpatil.lldMockInterviews.easy.documentReaderSystem;

public interface DocumentReader {
    void read(String filePath);
    default void readInChunks(String filePath, int chunkSize) {
        System.out.println("Chunk reading not supported for this document type.");
    }

}
