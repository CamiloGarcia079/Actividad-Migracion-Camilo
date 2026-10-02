package com.mindconnect.application.encounterstatus.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de encounter_statuses con el id pedido.
 */
public class EncounterStatusNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EncounterStatusNotFoundException(UUID id) {
        super("No se encontró un registro de encounter_statuses con id " + id);
    }
}
