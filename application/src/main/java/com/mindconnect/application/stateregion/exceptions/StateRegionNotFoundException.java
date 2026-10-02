package com.mindconnect.application.stateregion.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de state_regions con el id pedido.
 */
public class StateRegionNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public StateRegionNotFoundException(UUID id) {
        super("No se encontró un registro de state_regions con id " + id);
    }
}
