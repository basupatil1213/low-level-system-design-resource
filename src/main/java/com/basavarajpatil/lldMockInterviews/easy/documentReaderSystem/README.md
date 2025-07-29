# 📄 Document Reader System

A comprehensive document processing system that demonstrates multiple design patterns working together. This system can read various document formats (PDF, DOCX, TXT) using the Factory pattern for creation and Singleton pattern for instance management.

## 🎯 Problem Statement

Modern applications need to process documents in various formats without tightly coupling the code to specific document parsing libraries. The challenges include:

- Supporting multiple document formats (PDF, DOCX, TXT, etc.)
- Providing consistent reading interface across all formats
- Managing document reader instances efficiently
- Handling large files with chunked reading capabilities
- Adding new document formats without modifying existing code

## 💡 Solution Architecture

This system combines two design patterns:
- **Factory Pattern**: Creates appropriate document readers based on file type
- **Singleton Pattern**: Ensures single instance of each reader type for memory efficiency

## 🏗️ System Architecture

```
Client → DocumentReaderFactory → DocumentReader Interface
             ↓                          ↑
        DocumentReaderType         ┌─────┼─────┬─────┐
             (Enum)                │     │     │     │
                              TxtReader PDFReader DocxReader
                                 │     │        │
                            (Singleton)(Singleton)(Singleton)
```

## 📁 Implementation Components

### 1. DocumentReader Interface
```java
public interface DocumentReader {
    void read(String filePath);
    default void readInChunks(String filePath, int chunkSize);
}
```
**Responsibilities:**
- Defines contract for all document readers
- Provides basic `read()` method
- Offers optional `readInChunks()` with default implementation
- Enables polymorphic usage of different readers

### 2. DocumentReaderType Enum
```java
public enum DocumentReaderType {
    DOCX, PDF, TXT
}
```
**Responsibilities:**
- Type-safe document format identification
- Compile-time validation of supported formats
- Easy extension for new document types

### 3. Document Reader Implementations

#### TxtReader (Singleton)
```java
public class TxtReader implements DocumentReader {
    // Bill Pugh Singleton implementation
    private static class TxtReaderHolder {
        private static final TxtReader INSTANCE = new TxtReader();
    }
}
```
**Key Features:**
- **Real File I/O**: Actually reads text files using BufferedReader
- **Chunked Reading**: Supports reading large files in configurable chunks
- **Error Handling**: Graceful IOException handling
- **Memory Efficient**: Singleton ensures single instance

#### PDFReader (Singleton)
```java
public class PDFReader implements DocumentReader {
    // Simulates PDF parsing functionality
}
```
**Key Features:**
- **PDF Simulation**: Simulates PDF content extraction
- **Chunked Processing**: Demonstrates chunked reading for large PDFs
- **Singleton Pattern**: Memory-efficient instance management
- **Extensible**: Ready for real PDF library integration

#### DocxReader (Singleton)
```java
public class DocxReader implements DocumentReader {
    // Simulates DOCX parsing functionality
}
```
**Key Features:**
- **DOCX Simulation**: Simulates Microsoft Word document processing
- **Content Extraction**: Mimics text extraction from DOCX format
- **Singleton Pattern**: Ensures single reader instance
- **Future-Ready**: Prepared for Apache POI integration

### 4. DocumentReaderFactory
```java
public class DocumentReaderFactory {
    public static DocumentReader getDocumentReader(DocumentReaderType type) {
        switch (type) {
            case PDF -> PDFReader.getInstance();
            case TXT -> TxtReader.getInstance();
            case DOCX -> DocxReader.getInstance();
            default -> throw new IllegalArgumentException("Unknown DocumentReaderType: " + type);
        }
    }
}
```
**Responsibilities:**
- Creates appropriate document readers based on type
- Returns singleton instances for memory efficiency
- Uses modern Java switch expressions
- Provides clear error messages for unsupported types

## 🔧 Key Design Features

### Pattern Combination
- **Factory + Singleton**: Combines creation logic with instance management
- **Interface Segregation**: Clean separation of concerns
- **Type Safety**: Enum-based type system prevents runtime errors

### Performance Optimizations
- **Singleton Instances**: Memory-efficient reader management
- **Lazy Loading**: Readers created only when first accessed
- **Chunked Reading**: Efficient processing of large files

### Extensibility Features
- **Default Methods**: Interface provides default chunked reading
- **Enum Extension**: Easy addition of new document types
- **Plugin Architecture**: Ready for real parsing library integration

## 🧪 Usage Examples

### Basic Document Reading
```java
// Read a PDF document
DocumentReader pdfReader = DocumentReaderFactory.getDocumentReader(DocumentReaderType.PDF);
pdfReader.read("document.pdf");

// Read a text file
DocumentReader txtReader = DocumentReaderFactory.getDocumentReader(DocumentReaderType.TXT);
txtReader.read("document.txt");
```

### Chunked Reading for Large Files
```java
// Read large PDF in chunks
DocumentReader pdfReader = DocumentReaderFactory.getDocumentReader(DocumentReaderType.PDF);
pdfReader.readInChunks("large-document.pdf", 1024);

// Read large text file in chunks
DocumentReader txtReader = DocumentReaderFactory.getDocumentReader(DocumentReaderType.TXT);
txtReader.readInChunks("large-file.txt", 512);
```

### Dynamic Document Processing
```java
public class DocumentProcessor {
    public void processDocument(String filePath, DocumentReaderType type) {
        try {
            DocumentReader reader = DocumentReaderFactory.getDocumentReader(type);
            
            // Check file size and decide reading strategy
            if (isLargeFile(filePath)) {
                reader.readInChunks(filePath, 1024);
            } else {
                reader.read(filePath);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Unsupported document type: " + e.getMessage());
        }
    }
}
```

### Batch Document Processing
```java
public class BatchProcessor {
    public void processDocuments(List<String> filePaths) {
        for (String filePath : filePaths) {
            DocumentReaderType type = determineType(filePath);
            DocumentReader reader = DocumentReaderFactory.getDocumentReader(type);
            reader.read(filePath);
        }
    }
    
    private DocumentReaderType determineType(String filePath) {
        String extension = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
        return switch (extension) {
            case "pdf" -> DocumentReaderType.PDF;
            case "txt" -> DocumentReaderType.TXT;
            case "docx" -> DocumentReaderType.DOCX;
            default -> throw new IllegalArgumentException("Unsupported file type: " + extension);
        };
    }
}
```

## 🔄 Extending the System

### Adding Excel Support
```java
// 1. Update DocumentReaderType enum
public enum DocumentReaderType {
    DOCX, PDF, TXT, XLSX  // Add XLSX
}

// 2. Create XlsxReader
public class XlsxReader implements DocumentReader {
    private XlsxReader() {}
    
    private static class XlsxReaderHolder {
        private static final XlsxReader INSTANCE = new XlsxReader();
    }
    
    public static XlsxReader getInstance() {
        return XlsxReaderHolder.INSTANCE;
    }
    
    @Override
    public void read(String filePath) {
        System.out.println("Reading XLSX file: " + filePath);
        // Excel-specific reading logic
    }
}

// 3. Update Factory
case XLSX -> XlsxReader.getInstance();
```

### Adding PowerPoint Support
```java
// Similar pattern for PPTX files
public class PptxReader implements DocumentReader {
    // Singleton implementation
    // PowerPoint-specific logic
}
```

### Advanced Features Extension
```java
public interface AdvancedDocumentReader extends DocumentReader {
    void readWithMetadata(String filePath);
    List<String> extractImages(String filePath);
    void convertTo(String sourcePath, String targetPath, DocumentReaderType targetType);
}
```

## ✅ Design Patterns Demonstrated

### 1. Factory Pattern
- **Object Creation**: Centralized creation of document readers
- **Type Safety**: Enum-based type system
- **Extensibility**: Easy addition of new document types

### 2. Singleton Pattern
- **Instance Management**: Single instance per reader type
- **Memory Efficiency**: Reduces object creation overhead
- **Thread Safety**: Bill Pugh implementation

### 3. Strategy Pattern (Implicit)
- **Algorithm Selection**: Different reading strategies per format
- **Polymorphism**: Uniform interface across implementations

## ❌ Current Limitations

1. **Simulation Only**: PDF and DOCX readers simulate functionality
2. **Basic Error Handling**: Limited exception management
3. **No Caching**: No content caching mechanism
4. **Fixed Chunk Size**: No adaptive chunking based on file size
5. **No Parallel Processing**: Sequential reading only

## 🚀 Production Enhancements

### Real Library Integration
```java
// PDF Reader with Apache PDFBox
public class PDFReader implements DocumentReader {
    @Override
    public void read(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println(text);
        } catch (IOException e) {
            throw new DocumentReadingException("Failed to read PDF: " + e.getMessage());
        }
    }
}
```

### Asynchronous Processing
```java
public interface AsyncDocumentReader extends DocumentReader {
    CompletableFuture<String> readAsync(String filePath);
    CompletableFuture<Void> readInChunksAsync(String filePath, int chunkSize);
}
```

### Caching and Performance
```java
public class CachedDocumentReader implements DocumentReader {
    private final DocumentReader delegate;
    private final Cache<String, String> contentCache;
    
    @Override
    public void read(String filePath) {
        String content = contentCache.get(filePath, () -> {
            // Delegate to actual reader
            return delegate.extractContent(filePath);
        });
        System.out.println(content);
    }
}
```

## 🎓 Interview Questions & Answers

### Pattern-Related Questions

**Q**: Why combine Factory and Singleton patterns?  
**A**: Factory handles object creation logic while Singleton ensures memory efficiency. This combination provides both flexibility in object creation and resource optimization.

**Q**: What's the advantage of using enums for document types?  
**A**: Enums provide compile-time type safety, prevent invalid document types, enable IDE auto-completion, and make the code more maintainable.

**Q**: How does the default method in interface help?  
**A**: It provides a common implementation for chunked reading while allowing specific readers to override it with optimized versions.

### System Design Questions

**Q**: How would you handle a 10GB PDF file?  
**A**: Implement streaming with configurable chunk sizes, use memory mapping for large files, and consider async processing to avoid blocking operations.

**Q**: How would you add support for encrypted documents?  
**A**: Add a DecryptionService, modify readers to check for encryption, and implement a decorator pattern for encrypted document handling.

**Q**: How would you implement document format auto-detection?  
**A**: Use file magic numbers/signatures, MIME type detection, or library-based format detection before delegating to the appropriate reader.

### Performance Questions

**Q**: How would you optimize memory usage for large documents?  
**A**: Implement streaming readers, use lazy loading, add configurable buffer sizes, and consider pagination for display purposes.

**Q**: How would you handle concurrent document processing?  
**A**: Make readers thread-safe, implement async processing with thread pools, and add proper synchronization for shared resources.

## 🔗 Real-World Applications

- **Document Management Systems**: SharePoint, Google Drive file processing
- **Content Management**: WordPress, Drupal document handling
- **Email Systems**: Outlook, Gmail attachment processing
- **Legal Software**: Contract analysis and document review systems
- **Educational Platforms**: Assignment submission and grading systems

## 📊 Performance Characteristics

| Reader Type | Memory Usage | Processing Speed | Real I/O |
|-------------|--------------|------------------|----------|
| TxtReader   | Low          | Fast             | ✅ Yes   |
| PDFReader   | Medium       | Medium           | ❌ Simulated |
| DocxReader  | Medium       | Medium           | ❌ Simulated |

## 🔧 Running the Example

```bash
# Compile the document reader system
javac documentReaderSystem/*.java

# Run the demonstration
java documentReaderSystem.Client
```

**Expected Output:**
```
Reading PDF file: sample.pdf
Extracted text from PDF: "This is a sample PDF content."
Simulating reading PDF in chunks (chunk size = 10 chars): sample.pdf
This is a 
sample PDF
 content.
-----
Reading TXT file: src/main/java/.../sample.txt
Contents:
Hello, this is a sample text file.
[... actual file contents ...]
-----
Reading DOCX file: sample.docx
Extracted text from DOCX: "This is a sample DOCX content."
[... chunked output ...]
```

---

💡 **Key Takeaway**: This system demonstrates how multiple design patterns can work together to create a flexible, extensible, and maintainable document processing architecture.
