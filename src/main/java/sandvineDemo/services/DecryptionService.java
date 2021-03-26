package sandvineDemo.services;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

@Service
public class DecryptionService {


    public String getDecodedString(String encryptedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedString);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
