package com.porkerspicks.ppbf;

import com.porkerspicks.ppbf.betfair.service.BetPlacingEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class BetPlacingEngineIntegrationTest {

    @Autowired
    private BetPlacingEngine betPlacingEngine;

    @Test
    public void testPlaceBet() {

        //Run test
        betPlacingEngine.placeBets();
    }
}