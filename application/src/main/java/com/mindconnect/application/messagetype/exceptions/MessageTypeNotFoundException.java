package com.mindconnect.application.messagetype.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de message_types con el id pedido.
 */
public class MessageTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public MessageTypeNotFoundException(UUID id) {
        super("No se encontró un registro de message_types con id " + id);
    }
}
