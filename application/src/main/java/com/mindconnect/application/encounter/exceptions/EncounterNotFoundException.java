package com.mindconnect.application.encounter.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de encounters con el id pedido.
 */
public class EncounterNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EncounterNotFoundException(UUID id) {
        super("No se encontró un registro de encounters con id " + id);
    }
}
