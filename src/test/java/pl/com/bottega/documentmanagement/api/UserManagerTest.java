package pl.com.bottega.documentmanagement.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.documentmanagement.domain.EmployeeId;
import pl.com.bottega.documentmanagement.domain.repositories.EmployeeRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.mock.staticmock.AnnotationDrivenStaticEntityMockingControl.verify;

/**
 * Created by Piotr on 31.07.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private String occupiedLogin = "occupied ligin";
    private String anyPassword = "anypassword";
    private String anyLogin;

    @Mock
    private EmployeeId anyEmployeeId;

    @Test
    public void shouldFailSignupWhenLoginIsOccupied(){
        //given
        UserManager userManager = new UserManager(employeeRepository);
        when(employeeRepository.findByEmployeeId(anyEmployeeId)).thenReturn(null);
        when(employeeRepository.isLoginOccupied(occupiedLogin)).thenReturn(true);
        //when
        SignupResultDto signupResultDto = userManager.signup(occupiedLogin, anyPassword, anyEmployeeId);

        //then
        assertFalse(signupResultDto.isSuccess());
        assertEquals("login is occupied", signupResultDto.getFailureReason());

    }

    @Test
    public void shouldSignupEmployeeWhenIdAndLoginAreFree(){
        //given
        UserManager userManager = new UserManager(employeeRepository);
        when(employeeRepository.findByEmployeeId(anyEmployeeId)).thenReturn(null);
        when(employeeRepository.isLoginOccupied(anyLogin)).thenReturn(false);
        //when
        SignupResultDto signupResultDto = userManager.signup(anyLogin, anyPassword, anyEmployeeId);

        //then
        verify(employeeRepository).save
        assertTrue(signupResultDto.isSuccess());
        assertNull(signupResultDto.getFailureReason());

    }

}
