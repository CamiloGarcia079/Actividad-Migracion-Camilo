package com.mindconnect.application.conversationstatus.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de conversations_statuses con el id pedido.
 */
public class ConversationStatusNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ConversationStatusNotFoundException(UUID id) {
        super("No se encontró un registro de conversations_statuses con id " + id);
    }
}
