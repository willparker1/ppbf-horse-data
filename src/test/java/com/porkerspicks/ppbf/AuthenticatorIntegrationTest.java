package com.porkerspicks.ppbf;

import com.porkerspicks.ppbf.betfair.entities.LoginResponse;
import com.porkerspicks.ppbf.auth.Authenticator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@ActiveProfiles("test") // assuming you have a 'test' profile with test properties
public class AuthenticatorIntegrationTest {

    @Autowired
    private Authenticator authenticator;

    @Test
    public void testLogin() throws Exception {
        // Act
        LoginResponse result = authenticator.login();

        System.out.println("Session ID: " + result.getSessionToken());

        // Assert
        assertNotNull(result);
    }
}