package com.mindconnect.application.professionaltype.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de professional_types con el id pedido.
 */
public class ProfessionalTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProfessionalTypeNotFoundException(UUID id) {
        super("No se encontró un registro de professional_types con id " + id);
    }
}
