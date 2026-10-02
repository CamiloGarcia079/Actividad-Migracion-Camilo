package com.mindconnect.application.aimodel.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de ai_models con el id pedido.
 */
public class AiModelNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public AiModelNotFoundException(UUID id) {
        super("No se encontró un registro de ai_models con id " + id);
    }
}
