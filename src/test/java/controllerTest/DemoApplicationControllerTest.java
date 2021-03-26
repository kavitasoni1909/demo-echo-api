package controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sandvineDemo.services.AuthenticationService;
import sandvineDemo.DemoApplication;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class DemoApplicationControllerTest {
    @Autowired
    private MockMvc mvc;

    @Mock
    AuthenticationService authenticationService;

    @Test
    public void test_echoString() throws Exception {
        String originalInput = "Hello World";
        String encodedString = getEncodedString(originalInput);

        String uri = "/demo/echo?clientString="+encodedString;

        String password = "password";
        String encodedPassword = getEncodedString(password);

        HttpHeaders headers = new HttpHeaders();
        headers.add("username", "test user");
        headers.add("password", encodedPassword);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(originalInput, content);

    }

    private String getEncodedString(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    @Test
    public void test_echoString_withEmptyParam() throws Exception {

        String password = "password";
        String encodedPassword = getEncodedString(password);

        HttpHeaders headers = new HttpHeaders();
        headers.add("username", "test user");
        headers.add("password", encodedPassword);

        String uri = "/demo/echo?clientString=";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).headers(headers)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("", content);

    }
}
