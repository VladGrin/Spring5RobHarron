package org.example.service;

public class NotimplementedException extends RuntimeException {


    public NotimplementedException() {
        super();
    }

    public NotimplementedException(String message) {
        super(message);
    }

    public NotimplementedException(String message, Throwable cause) {
        super(message, cause);
    }
}
