package com.corporation.task05.service.exception;

public class XmlParsingServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public XmlParsingServiceException() {
        super();
    }

    public XmlParsingServiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public XmlParsingServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParsingServiceException(String message) {
        super(message);
    }

    public XmlParsingServiceException(Throwable cause) {
        super(cause);
    }

}
