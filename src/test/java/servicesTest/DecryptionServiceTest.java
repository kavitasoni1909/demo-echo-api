package servicesTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import sandvineDemo.services.DecryptionService;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DecryptionServiceTest {

    @InjectMocks
    DecryptionService decryptionService;

    @Test
    public void test_getDecryptedString(){
        String originalInput = "Hello World";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

        String decodedString = decryptionService.getDecodedString(encodedString);
        assertEquals(originalInput, decodedString);
    }

    @Test
    public void test_getDecryptedString_withEmptyString(){
        String originalInput = "";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

        String decodedString = decryptionService.getDecodedString(encodedString);
        assertEquals(originalInput, decodedString);
    }
}
