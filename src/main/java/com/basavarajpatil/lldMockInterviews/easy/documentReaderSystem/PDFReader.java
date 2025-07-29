package com.basavarajpatil.lldMockInterviews.easy.documentReaderSystem;

public class PDFReader implements DocumentReader{

    private PDFReader (){}

    private static class PDFReaderHolder{
        private static final PDFReader INSTANCE = new PDFReader();
    }

    public static PDFReader getInstance(){
        return PDFReaderHolder.INSTANCE;
    }

    @Override
    public void read(String filePath) {
        System.out.println("Reading PDF file: " + filePath);
        System.out.println("Extracted text from PDF: \"This is a sample PDF content.\"");
    }

    @Override
    public void readInChunks(String filePath, int chunkSize) {
        System.out.println("Simulating reading PDF in chunks (chunk size = " + chunkSize + " chars): " + filePath);
        // Simulate chunks with fixed string split
        String content = "This is a sample PDF content.";
        for (int i = 0; i < content.length(); i += chunkSize) {
            System.out.println(content.substring(i, Math.min(i + chunkSize, content.length())));
        }
    }
}
