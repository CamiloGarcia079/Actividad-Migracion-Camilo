package com.mindconnect.application.encountertype.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de encounter_types con el id pedido.
 */
public class EncounterTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EncounterTypeNotFoundException(UUID id) {
        super("No se encontró un registro de encounter_types con id " + id);
    }
}
