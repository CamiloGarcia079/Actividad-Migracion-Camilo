package com.mindconnect.application.professionalstudy.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de professional_studies con el id pedido.
 */
public class ProfessionalStudyNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProfessionalStudyNotFoundException(UUID id) {
        super("No se encontró un registro de professional_studies con id " + id);
    }
}
