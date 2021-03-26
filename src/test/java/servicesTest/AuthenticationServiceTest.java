package servicesTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import sandvineDemo.services.AuthenticationService;
import sandvineDemo.services.DecryptionService;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @Mock
    DecryptionService decryptionService;

    @InjectMocks
    AuthenticationService authenticationService;

    @Test
    public void test_validUser(){
        String password = "password";
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());

        given(decryptionService.getDecodedString(encodedPassword)).willReturn(password);

        boolean isValid = authenticationService.isUserValid("test user", encodedPassword);
        assertEquals(true, isValid);
    }

    @Test
    public void test_invalidUser(){
        String password = "invalidPassword";
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());

        given(decryptionService.getDecodedString(encodedPassword)).willReturn(password);

        boolean isValid = authenticationService.isUserValid("test user", encodedPassword);
        assertEquals(false, isValid);
    }
}
