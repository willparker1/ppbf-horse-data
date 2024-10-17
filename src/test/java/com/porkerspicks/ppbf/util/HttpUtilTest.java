package com.porkerspicks.ppbf.util;

import com.porkerspicks.ppbf.betfair.BetfairConfig;
import com.porkerspicks.ppbf.betfair.entities.EventTypeResult;
import com.porkerspicks.ppbf.betfair.enums.ApiNgOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HttpUtilTest {

    @Mock
    private BetfairConfig betfairConfig;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HttpUtil httpUtil;

    @BeforeEach
    public void setup() {
        when(betfairConfig.getApplicationKey()).thenReturn("appKey");
        when(betfairConfig.getApiUrl()).thenReturn("http://api.betfair.com/");
        when(betfairConfig.getContentType()).thenReturn("application/json");
    }

    @Test
    public void testSendPostRequest() {

        EventTypeResult[] eventTypes = new EventTypeResult[] { new EventTypeResult() };
        Class<EventTypeResult[]> clazz = EventTypeResult[].class;
        when(restTemplate.postForObject(any(String.class), any(), eq(clazz))).thenReturn( eventTypes );

        Map<String, Object> params = new HashMap<>();
        EventTypeResult[] response = httpUtil.sendPostRequest(params, ApiNgOperation.LISTEVENTTYPES);

        assertEquals(response, eventTypes);
    }
}