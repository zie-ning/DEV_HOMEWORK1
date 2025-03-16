package com.example.homework1.util;

public class NotPresentException extends RuntimeException {
    public NotPresentException() {
        super();
    }

    public NotPresentException(String message) {
        super(message);
    }

    public NotPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPresentException(Throwable cause) {
        super(cause);
    }
}
