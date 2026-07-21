package com.annmweru.library.exception;

public class BookAlreadyAvailableException extends RuntimeException {
    public BookAlreadyAvailableException(String message) {
        super(message);
    }
}
