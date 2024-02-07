package com.example.model.exception;

public class DemoAppException extends RuntimeException{
    public DemoAppException() {
        super();
    }

    public DemoAppException(String message) {
        super(message);
    }

    public DemoAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoAppException(Throwable cause) {
        super(cause);
    }

    protected DemoAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
