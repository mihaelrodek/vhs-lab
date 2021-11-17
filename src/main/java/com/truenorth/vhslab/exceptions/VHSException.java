package com.truenorth.vhslab.exceptions;

public class VHSException extends RuntimeException {

    public VHSException(String message, Throwable cause) {
        super(message, cause);
    }

    public VHSException(String message) {
        super(message);
    }

    public VHSException(Throwable cause) {
        super(cause);
    }

    public VHSException(String message, Throwable cause,
                          boolean enableSuppression,
                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
