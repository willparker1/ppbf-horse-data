package com.porkerspicks.ppbf;

import com.porkerspicks.ppbf.mail.GMailer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class GMailerIntegrationTest {

    @Autowired
    private GMailer gmailer;

    @Test
    public void testSendMessage() throws Exception {
        // Act
//        Message message = gmailer.sendMessage("test", "test");
//
//        // Assert
//        assertNotNull(message);
//        System.out.println(message);
    }
}