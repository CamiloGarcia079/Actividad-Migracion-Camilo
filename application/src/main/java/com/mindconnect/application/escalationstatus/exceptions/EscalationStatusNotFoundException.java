package com.mindconnect.application.escalationstatus.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de escalations_statuses con el id pedido.
 */
public class EscalationStatusNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EscalationStatusNotFoundException(UUID id) {
        super("No se encontró un registro de escalations_statuses con id " + id);
    }
}
