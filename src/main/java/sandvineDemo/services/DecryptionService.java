package sandvineDemo.services;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class DecryptionService {


    public String getDecodedString(String encryptedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedString);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
