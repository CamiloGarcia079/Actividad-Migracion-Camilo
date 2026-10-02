package com.mindconnect.application.consenttype.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de consent_types con el id pedido.
 */
public class ConsentTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ConsentTypeNotFoundException(UUID id) {
        super("No se encontró un registro de consent_types con id " + id);
    }
}
