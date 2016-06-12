package pl.com.bottega.documentmanagement.api;

import pl.com.bottega.documentmanagement.domain.DocumentNumber;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Piotr on 12.06.2016.
 */
public class DocumentFlowProcess {

    public DocumentNumber create(String title, String content){
        checkNotNull(title);
        checkNotNull(content);
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
