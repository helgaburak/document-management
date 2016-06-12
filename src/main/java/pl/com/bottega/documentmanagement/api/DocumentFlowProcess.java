package pl.com.bottega.documentmanagement.api;

import pl.com.bottega.documentmanagement.domain.Document;
import pl.com.bottega.documentmanagement.domain.DocumentNumber;
import pl.com.bottega.documentmanagement.domain.DocumentNumberGenerator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Piotr on 12.06.2016.
 */
public class DocumentFlowProcess {

    private DocumentNumberGenerator documentNumberGenerator;
    private DocumentRepository documentRepository;
    public DocumentNumber create(String title, String content){
        checkNotNull(title);
        checkNotNull(content);
        DocumentNumber documentNumber = documentNumberGenerator.generate();
        Document document = new Document(documentNumber, title, content);

        return null;
    }

    public void change(DocumentNumber documentNumber, String newTitle, String newContent){
        checkNotNull(newTitle);
        checkNotNull(documentNumber);
        checkNotNull(newContent);
    }

    public void verify(DocumentNumber documentNumber){
        checkNotNull(documentNumber);
    }

    public void publish(DocumentNumber documentNumber ){
        checkNotNull(documentNumber);

    }

    public void archive(DocumentNumber documentNumber){
        checkNotNull(documentNumber);

    }

    public DocumentNumber newVersion(DocumentNumber documentNumber){
        checkNotNull(documentNumber);
        return null;
    }

}
