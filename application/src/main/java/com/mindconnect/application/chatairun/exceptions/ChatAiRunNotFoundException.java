package com.mindconnect.application.chatairun.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_ai_runs con el id pedido.
 */
public class ChatAiRunNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunNotFoundException(UUID id) {
        super("No se encontró un registro de chat_ai_runs con id " + id);
    }
}
