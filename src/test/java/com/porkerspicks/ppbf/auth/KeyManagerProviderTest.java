package com.porkerspicks.ppbf.auth;

import com.porkerspicks.ppbf.betfair.BetfairConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.KeyStore;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KeyManagerProviderTest {

    @Mock
    private BetfairConfig betfairConfig;

    @InjectMocks
    private KeyManagerProvider keyManagerProvider;

    @BeforeEach
    public void setup() {
        // This is brittle and needs to be fixed
        when(betfairConfig.getKeyStorePath()).thenReturn("C:\\dev\\openssl\\client-2048.p12");
    }

    @Test
    public void shouldReturnKeyManagersWhenGivenValidInputs() throws Exception {
        KeyStore keyStore = keyManagerProvider.getKeyStore();
        assertNotNull(keyStore);
    }
}