package com.truenorth.vhslab.exceptions;

import com.truenorth.vhslab.responses.ErrorType;
import com.truenorth.vhslab.responses.RegistrationField;

public class ValidationException extends Exception {
    private RegistrationField field;
    private ErrorType error;

    public ValidationException(RegistrationField field, ErrorType error) {
        this("", field, error);
    }

    public ValidationException(String message, RegistrationField field, ErrorType error) {
        super(message);
        this.field = field;
        this.error = error;
    }

    public RegistrationField getField() {
        return field;
    }

    public ErrorType getError() {
        return error;
    }
}

