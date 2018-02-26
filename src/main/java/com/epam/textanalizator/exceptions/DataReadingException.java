package com.epam.textanalizator.exceptions;

public class DataReadingException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataReadingException(String message) {
        super(message);
    }
    
    public DataReadingException(String message, Throwable cause) {
        super(message);
    }

    public DataReadingException(Throwable cause) {
        super();
    }

    public DataReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super();
    }
}
