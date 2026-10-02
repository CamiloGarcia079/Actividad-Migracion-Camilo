package com.mindconnect.application.chatconversation.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_conversations con el id pedido.
 */
public class ChatConversationNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatConversationNotFoundException(UUID id) {
        super("No se encontró un registro de chat_conversations con id " + id);
    }
}
