package com.mindconnect.application.chatmessage.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatmessage.dtos.ChatMessageCommand;
import com.mindconnect.domain.chatmessage.model.aggregates.ChatMessage;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_messages.
 */
public interface ChatMessageUseCase {

    ChatMessage create(ChatMessageCommand command);

    ChatMessage getById(UUID id);

    List<ChatMessage> getAll();

    ChatMessage update(UUID id, ChatMessageCommand command);

    void delete(UUID id);
}
