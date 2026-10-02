package com.mindconnect.application.airunstatus.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de ai_runs_statuses con el id pedido.
 */
public class AiRunStatusNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public AiRunStatusNotFoundException(UUID id) {
        super("No se encontró un registro de ai_runs_statuses con id " + id);
    }
}
