package com.epam.textanalizator.exceptions;

public class DataReadingException extends Exception {

    public DataReadingException(String message, Throwable cause) {
        super(message);
    }

    public DataReadingException(Throwable cause) {
        super();
    }
}
