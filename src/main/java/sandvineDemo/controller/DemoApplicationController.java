package sandvineDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sandvineDemo.services.AuthenticationService;
import sandvineDemo.services.DecryptionService;

@RestController
@RequestMapping("/demo")
public class DemoApplicationController {

    @Autowired
    DecryptionService decryptionService;

    @Autowired
    AuthenticationService authenticationService;

    @CrossOrigin
    @GetMapping("/echo")
    public String echoString(@RequestParam(value = "clientString", defaultValue = "") String clientString, @RequestHeader("username") String userName, @RequestHeader("password") String encodedPassword) {
        boolean isValidUser = authenticationService.isUserValid(userName, encodedPassword);
        if(isValidUser){
            String decryptedClientString = decryptionService.getDecodedString(clientString);
            System.out.println(clientString);
            return decryptedClientString;
        }
        return null;
    }
}
