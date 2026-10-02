package com.mindconnect.application.patient.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de patients con el id pedido.
 */
public class PatientNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PatientNotFoundException(UUID id) {
        super("No se encontró un registro de patients con id " + id);
    }
}
