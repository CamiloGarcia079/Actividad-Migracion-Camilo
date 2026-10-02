package com.mindconnect.application.medicationroute.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de medication_routes con el id pedido.
 */
public class MedicationRouteNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public MedicationRouteNotFoundException(UUID id) {
        super("No se encontró un registro de medication_routes con id " + id);
    }
}
