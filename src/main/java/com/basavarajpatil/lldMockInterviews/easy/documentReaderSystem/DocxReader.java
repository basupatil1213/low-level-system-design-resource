package com.basavarajpatil.lldMockInterviews.easy.documentReaderSystem;

public class DocxReader implements DocumentReader{
    private DocxReader (){}

    private static class DocxReaderHolder{
        private static final DocxReader INSTANCE = new DocxReader();
    }

    public static DocxReader getInstance(){
        return DocxReaderHolder.INSTANCE;
    }

    @Override
    public void read(String filePath) {
        System.out.println("Reading DOCX file: " + filePath);
        System.out.println("Extracted text from DOCX: \"This is a sample DOCX content.\"");
    }

    @Override
    public void readInChunks(String filePath, int chunkSize) {
        System.out.println("Simulating reading DOCX in chunks (chunk size = " + chunkSize + " chars): " + filePath);
        String content = "This is a sample DOCX content.";
        for (int i = 0; i < content.length(); i += chunkSize) {
            System.out.println(content.substring(i, Math.min(i + chunkSize, content.length())));
        }
    }
}
