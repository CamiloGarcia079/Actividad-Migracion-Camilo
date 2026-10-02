package com.mindconnect.application.gender.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de genders con el id pedido.
 */
public class GenderNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public GenderNotFoundException(UUID id) {
        super("No se encontró un registro de genders con id " + id);
    }
}
