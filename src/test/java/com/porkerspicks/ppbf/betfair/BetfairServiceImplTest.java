package com.porkerspicks.ppbf.betfair;

import com.porkerspicks.ppbf.betfair.entities.EventTypeResult;
import com.porkerspicks.ppbf.betfair.entities.MarketFilter;
import com.porkerspicks.ppbf.betfair.exceptions.APINGException;
import com.porkerspicks.ppbf.betfair.service.BetfairServiceImpl;
import com.porkerspicks.ppbf.util.HttpUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BetfairServiceImplTest {

    @Mock
    private BetfairConfig betfairConfig;

    @Mock
    private HttpUtil httpUtil;

    @InjectMocks
    private BetfairServiceImpl betfairServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListEventTypes() throws APINGException {
        // Arrange
        MarketFilter filter = new MarketFilter();
        String appKey = "testAppKey";
        String ssoId = "testSsoId";
        EventTypeResult eventTypeResult = new EventTypeResult();
        eventTypeResult.setEventType(null);
        when(httpUtil.sendPostRequest(any(), any())).thenReturn(new EventTypeResult[]{eventTypeResult});

        // Act
        List<EventTypeResult> result = betfairServiceImpl.listEventTypes(filter);

        // Assert
        assertEquals(Collections.singletonList(eventTypeResult), result);
    }
}
