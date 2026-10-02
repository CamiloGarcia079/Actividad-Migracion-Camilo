package com.mindconnect.application.chatescalation.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_escalations con el id pedido.
 */
public class ChatEscalationNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationNotFoundException(UUID id) {
        super("No se encontró un registro de chat_escalations con id " + id);
    }
}
