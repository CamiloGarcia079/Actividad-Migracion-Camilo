package com.mindconnect.application.country.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de countries con el id pedido.
 */
public class CountryNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(UUID id) {
        super("No se encontró un registro de countries con id " + id);
    }
}
