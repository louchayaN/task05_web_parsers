package com.corporation.task05.dao.exception;

public class XmlParsingDaoException extends Exception {

    private static final long serialVersionUID = 1L;

    public XmlParsingDaoException() {
        super();
    }

    public XmlParsingDaoException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public XmlParsingDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParsingDaoException(String message) {
        super(message);
    }

    public XmlParsingDaoException(Throwable cause) {
        super(cause);
    }

}
