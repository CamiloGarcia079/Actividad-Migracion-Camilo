package com.mindconnect.application.chatconversation.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatconversation.dtos.ChatConversationCommand;
import com.mindconnect.domain.chatconversation.model.aggregates.ChatConversation;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_conversations.
 */
public interface ChatConversationUseCase {

    ChatConversation create(ChatConversationCommand command);

    ChatConversation getById(UUID id);

    List<ChatConversation> getAll();

    ChatConversation update(UUID id, ChatConversationCommand command);

    void delete(UUID id);
}
