package com.mindconnect.application.chatconversationaisetting.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de chat_conversation_ai_settings con el id pedido.
 */
public class ChatConversationAiSettingNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatConversationAiSettingNotFoundException(UUID id) {
        super("No se encontró un registro de chat_conversation_ai_settings con id " + id);
    }
}
