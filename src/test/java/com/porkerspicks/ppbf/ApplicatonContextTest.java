package com.porkerspicks.ppbf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class ApplicatonContextTest {

    @Test
    public void contextLoads() {
        // This test will fail if the application context cannot start
    }
}