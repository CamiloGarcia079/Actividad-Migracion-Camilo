package com.mindconnect.application.chatparticipant.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_participants con el id pedido.
 */
public class ChatParticipantNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatParticipantNotFoundException(UUID id) {
        super("No se encontró un registro de chat_participants con id " + id);
    }
}
