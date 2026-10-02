package com.mindconnect.application.chatmessage.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_messages con el id pedido.
 */
public class ChatMessageNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatMessageNotFoundException(UUID id) {
        super("No se encontró un registro de chat_messages con id " + id);
    }
}
