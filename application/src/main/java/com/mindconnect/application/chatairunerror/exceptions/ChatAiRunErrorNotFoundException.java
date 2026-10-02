package com.mindconnect.application.chatairunerror.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_ai_run_errors con el id pedido.
 */
public class ChatAiRunErrorNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunErrorNotFoundException(UUID id) {
        super("No se encontró un registro de chat_ai_run_errors con id " + id);
    }
}
