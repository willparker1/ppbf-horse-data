package com.porkerspicks.ppbf.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.mock.http.client.MockClientHttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoggingRequestInterceptorTest {

    @Mock
    private ClientHttpRequestExecution execution;

    @Mock
    private ClientHttpResponse response;

    private LoggingRequestInterceptor interceptor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        interceptor = new LoggingRequestInterceptor();
    }

    @Test
    public void testIntercept() throws URISyntaxException, IOException {
        MockClientHttpRequest request = new MockClientHttpRequest(HttpMethod.GET, new URI("http://localhost"));
        byte[] body = "body".getBytes();
        ClientHttpResponse response = new MockClientHttpResponse( "responseBody".getBytes(), HttpStatus.ACCEPTED);

        when(execution.execute(any(), any())).thenReturn(response);

        interceptor.intercept(request, body, execution);
    }
}