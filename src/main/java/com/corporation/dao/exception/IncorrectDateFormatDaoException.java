package com.corporation.dao.exception;

public class IncorrectDateFormatDaoException extends Exception {

    private static final long serialVersionUID = 1L;

    public IncorrectDateFormatDaoException() {
        super();
    }

    public IncorrectDateFormatDaoException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncorrectDateFormatDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDateFormatDaoException(String message) {
        super(message);
    }

    public IncorrectDateFormatDaoException(Throwable cause) {
        super(cause);
    }
}
