package com.corporation.task05.service.exception;

public class UnsupportedParserTypeServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnsupportedParserTypeServiceException() {
        super();
    }

    public UnsupportedParserTypeServiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnsupportedParserTypeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedParserTypeServiceException(String message) {
        super(message);
    }

    public UnsupportedParserTypeServiceException(Throwable cause) {
        super(cause);
    }

}
