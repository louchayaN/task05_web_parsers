package com.corporation.task05.dao.exception;

public class UnsupportedParserTypeDaoException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnsupportedParserTypeDaoException() {
        super();
    }

    public UnsupportedParserTypeDaoException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnsupportedParserTypeDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedParserTypeDaoException(String message) {
        super(message);
    }

    public UnsupportedParserTypeDaoException(Throwable cause) {
        super(cause);
    }

}
