package com.mindconnect.application.assessmenttype.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de assessment_types con el id pedido.
 */
public class AssessmentTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public AssessmentTypeNotFoundException(UUID id) {
        super("No se encontró un registro de assessment_types con id " + id);
    }
}
