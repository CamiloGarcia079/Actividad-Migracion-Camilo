package com.mindconnect.application.shared.exceptions;

/**
 * Error propio de la capa de aplicación (por ejemplo, un registro que no existe).
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApplicationException(String message) {
        super(message);
    }
}
