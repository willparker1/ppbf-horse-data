package com.porkerspicks.ppbf.betfair.exceptions;

public class PpbfException extends RuntimeException {

    public PpbfException() {
        super();
    }

    public PpbfException(String message) {
        super(message);
    }

    public PpbfException(String message, Throwable cause) {
        super(message, cause);
    }

    public PpbfException(Throwable cause) {
        super(cause);
    }
}