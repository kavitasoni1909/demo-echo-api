package sandvineDemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private static String validUsername = "test user";
    private static String validPassword = "password";

    @Autowired
    DecryptionService decryptionService;

    public boolean isUserValid(String username, String encodedPassword){
        String decodedPassword = decryptionService.getDecodedString(encodedPassword);
        return validUsername.equals(username) && validPassword.equals(decodedPassword);
    }
}
