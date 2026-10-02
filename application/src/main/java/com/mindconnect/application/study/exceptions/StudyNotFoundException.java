package com.mindconnect.application.study.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de studies con el id pedido.
 */
public class StudyNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public StudyNotFoundException(UUID id) {
        super("No se encontró un registro de studies con id " + id);
    }
}
