package pl.com.bottega.documentmanagement.api;

import pl.com.bottega.documentmanagement.domain.Document;
import pl.com.bottega.documentmanagement.domain.DocumentNumber;
import pl.com.bottega.documentmanagement.domain.Employee;
import pl.com.bottega.documentmanagement.domain.repositories.DocumentRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Piotr on 12.06.2016.
 */
public class ReadingConfirmator {
    private DocumentRepository documenRepository;
    private UserManager userManager;

    public void confirm(DocumentNumber documentNumber){
        checkNotNull(documentNumber);

        Document document = documenRepository.load(documentNumber);
        document.confirm(userManager.currentEmployee());
    }

    public void confirm(DocumentNumber documentNumber, Employee forEmployee){
        checkNotNull(documentNumber);
        checkNotNull(forEmployee);

    }
}
