package com.mindconnect.application.priority.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de priorities con el id pedido.
 */
public class PriorityNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PriorityNotFoundException(UUID id) {
        super("No se encontró un registro de priorities con id " + id);
    }
}
