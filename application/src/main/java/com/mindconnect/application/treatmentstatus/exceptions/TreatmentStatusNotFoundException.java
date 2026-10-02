package com.mindconnect.application.treatmentstatus.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de treatment_statuses con el id pedido.
 */
public class TreatmentStatusNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public TreatmentStatusNotFoundException(UUID id) {
        super("No se encontró un registro de treatment_statuses con id " + id);
    }
}
