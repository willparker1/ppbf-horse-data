package com.porkerspicks.ppbf.betfair.enums;

public enum LoginStatusCode {

    SUCCESS("Success"),
    LIMITED_ACCESS("Access is limited (e.g. accounts can login but can't bet due to account suspension), product session will be provided."),
    LOGIN_RESTRICTED("Login is restricted (in case of indirection point this is what will be returned), product session will not be provided:"),
    CERT_AUTH_REQUIRED("Certificate required or certificate present but could not authenticate with it."),
    FAIL("See error code - product session will not be provided");

    private final String description;

    LoginStatusCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}