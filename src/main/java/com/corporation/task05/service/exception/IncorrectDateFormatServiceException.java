package com.corporation.task05.service.exception;

public class IncorrectDateFormatServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public IncorrectDateFormatServiceException() {
        super();
    }

    public IncorrectDateFormatServiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncorrectDateFormatServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDateFormatServiceException(String message) {
        super(message);
    }

    public IncorrectDateFormatServiceException(Throwable cause) {
        super(cause);
    }

}
