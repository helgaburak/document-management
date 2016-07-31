package pl.com.bottega.documentmanagement.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by Piotr on 31.07.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentTest {

    @Mock
    private DocumentNumber anyNumber;
    @Mock
    private Employee anyEmloyee;

    private String anyTitle = "test title";

    private String anyContent = "test content";

    @Mock
    private Employee anyVerificator;
    private long EPS= 2L*1000L;


    @Test
    public void shouldCreateDocumentWithInitialState(){

        //given
        DocumentNumber number = mock(DocumentNumber.class);
        //String content = "test content";
        //String title = "test title";
        Employee employee = mock(Employee.class);

        //when
        Document  document = new Document(number, anyContent, anyTitle, employee);

        //then
        assertEquals(number, document.number());
        assertEquals(anyContent, document.content());
        assertEquals(anyTitle, document.title());
        assertEquals(employee, document.creator());
        assertFalse(document.deleted());
        assertEquals(DocumentStatus.DRAFT, document.status());
    }

    @Test
    public void shouldVerifyDocument(){
        //given
        Document  document = new Document(anyNumber, anyContent, anyTitle, anyEmloyee);

        //when
        document.verify(anyVerificator);
        //then
        assertTrue(Math.abs(new Date().getTime() - document.verifiedAt().getTime()) < EPS);
        assertEquals(DocumentStatus.VERIFIED, document.status());
        assertEquals(anyVerificator, document.getVerificator());

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireVerificator(){
        //given
        Document  document = new Document(anyNumber, anyContent, anyTitle, anyEmloyee);
        //when
        document.verify(null);
        //then

    }

    @Test
    public void shouldRequireVerificatorOtherWay(){
        //given
        Document  document = new Document(anyNumber, anyContent, anyTitle, anyEmloyee);
        //when
        try {
            document.verify(null);
        }
        catch (IllegalArgumentException ex) {
            return;
        }

        //then

    }

}
