package com.basavarajpatil.lldMockInterviews.easy.documentReaderSystem;

public class DocumentReaderFactory {

    public static DocumentReader getDocumentReader(DocumentReaderType type) {
        switch (type){
            case PDF -> {
                return PDFReader.getInstance();
            }
            case TXT -> {
                return TxtReader.getInstance();
            }
            case DOCX -> {
                return DocxReader.getInstance();
            }
            default -> {
                throw new IllegalArgumentException("Unknown DocumentReaderType: " + type);
            }
        }
    }
}
