package com.mindconnect.application.professional.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de professionals con el id pedido.
 */
public class ProfessionalNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProfessionalNotFoundException(UUID id) {
        super("No se encontró un registro de professionals con id " + id);
    }
}
