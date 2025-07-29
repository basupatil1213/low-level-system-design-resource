package com.basavarajpatil.lldMockInterviews.easy.documentReaderSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Client {
    public static void main(String[] args) {
        // Get PDF Reader and read file
        DocumentReader pdfReader = DocumentReaderFactory.getDocumentReader(DocumentReaderType.PDF);
        pdfReader.read("sample.pdf");
        pdfReader.readInChunks("sample.pdf", 10);

        System.out.println("-----");

        // Append filename to the current directory path
        String filePath = "src/main/java/com/basavarajpatil/lldMockInterviews/easy/documentReaderSystem/sample.txt";

        // Get TXT Reader and read file
        DocumentReader txtReader = DocumentReaderFactory.getDocumentReader(DocumentReaderType.TXT);
        txtReader.read(filePath);
        txtReader.readInChunks("sample.txt", 15);

        System.out.println("-----");

        // Get DOCX Reader and read file
        DocumentReader docxReader = DocumentReaderFactory.getDocumentReader(DocumentReaderType.DOCX);
        docxReader.read("sample.docx");
        docxReader.readInChunks("sample.docx", 12);
    }
}
