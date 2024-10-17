package com.porkerspicks.ppbf.auth;

import com.porkerspicks.ppbf.betfair.BetfairConfig;
import com.porkerspicks.ppbf.betfair.enums.LoginErrorCode;
import com.porkerspicks.ppbf.betfair.enums.LoginStatusCode;
import com.porkerspicks.ppbf.betfair.entities.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticatorTest {

    @Mock
    private BetfairConfig betfairConfig;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private Authenticator authenticator;

    @BeforeEach
    public void setup() {
        when(betfairConfig.getAuthUrl()).thenReturn("url");
        when(betfairConfig.getUsername()).thenReturn("username");
        when(betfairConfig.getPassword()).thenReturn("password");
    }

    @Test
    @DisplayName("Should return session when login is successful")
    public void shouldReturnSessionWhenLoginIsSuccessful() throws Exception {

        // Mock a valid session
        LoginResponse validLoginResponse = new LoginResponse();
        validLoginResponse.setLoginStatus(LoginStatusCode.SUCCESS);
        validLoginResponse.setSessionToken("TESTTESTTEST");
        ResponseEntity<LoginResponse> responseEntity = ResponseEntity.ok(validLoginResponse);
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenReturn(responseEntity);

        LoginResponse actualLoginResponse = authenticator.login();

        assertNotNull(actualLoginResponse);
    }

    @Test
    @DisplayName("Should throw exception when response body is null")
    public void shouldThrowExceptionWhenResponseBodyIsNull() {
        ResponseEntity<LoginResponse> responseEntity = ResponseEntity.ok(null);
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenReturn(responseEntity);

        assertThrows(RestClientException.class, () -> authenticator.login());
    }

    @Test
    @DisplayName("Should throw exception when HttpClientErrorException is thrown")
    public void shouldThrowExceptionWhenHttpClientErrorExceptionIsThrown() {
        HttpClientErrorException httpClientErrorException = new HttpClientErrorException( HttpStatus.BAD_REQUEST, "Bad Request" );
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenThrow(httpClientErrorException );

        assertThrows(HttpClientErrorException.class, () -> authenticator.login());
    }

    @Test
    @DisplayName("Should throw exception when HttpServerErrorException is thrown")
    public void shouldThrowExceptionWhenHttpServerErrorExceptionIsThrown() {
        HttpServerErrorException httpServerErrorException = new HttpServerErrorException( HttpStatus.BAD_REQUEST, "Bad Request" );
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenThrow(httpServerErrorException);

        assertThrows(HttpServerErrorException.class, () -> authenticator.login());
    }


    @Test
    @DisplayName("Should throw exception when RestClientException is thrown")
    public void shouldThrowExceptionWhenRestClientExceptionIsThrown() {
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenThrow(RestClientException.class);

        assertThrows(RestClientException.class, () -> authenticator.login());
    }

    @Test
    @DisplayName("Should throw exception when session token is null")
    public void shouldThrowExceptionWhenSessionTokenIsNull() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginStatus(LoginStatusCode.FAIL);
        loginResponse.setError(LoginErrorCode.ACCOUNT_ALREADY_LOCKED);
        ResponseEntity<LoginResponse> responseEntity = ResponseEntity.ok(loginResponse);
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenReturn(responseEntity);

        assertThrows(RestClientException.class, () -> authenticator.login());
    }

    @Test
    @DisplayName("Should throw exception when status code is not 200")
    public void shouldThrowExceptionWhenStatusCodeIsNot200() {
        LoginResponse loginResponse = new LoginResponse();
        ResponseEntity<LoginResponse> responseEntity = new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenReturn(responseEntity);

        assertThrows(RestClientException.class, () -> authenticator.login());
    }

    @Test
    @DisplayName("Should throw exception when login response is not successful")
    public void shouldThrowExceptionWhenLoginResponseIsNotSuccessful() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginStatus(LoginStatusCode.FAIL);
        loginResponse.setError(LoginErrorCode.ACCOUNT_ALREADY_LOCKED);
        ResponseEntity<LoginResponse> responseEntity = ResponseEntity.ok(loginResponse);
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenReturn(responseEntity);

        assertThrows(RestClientException.class, () -> authenticator.login());
    }

    @Test
    @DisplayName("Should handle login response is not successful but login error is null")
    public void shouldThrowExceptionWhenLoginErrorIsNull() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginStatus(LoginStatusCode.FAIL); // Assuming FAILURE is a possible status
        ResponseEntity<LoginResponse> responseEntity = ResponseEntity.ok(loginResponse);
        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), eq(LoginResponse.class))).thenReturn(responseEntity);

        assertThrows(RestClientException.class, () -> authenticator.login());
    }
}
