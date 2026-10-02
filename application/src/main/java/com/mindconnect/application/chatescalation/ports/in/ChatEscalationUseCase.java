package com.mindconnect.application.chatescalation.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatescalation.dtos.ChatEscalationCommand;
import com.mindconnect.domain.chatescalation.model.aggregates.ChatEscalation;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_escalations.
 */
public interface ChatEscalationUseCase {

    ChatEscalation create(ChatEscalationCommand command);

    ChatEscalation getById(UUID id);

    List<ChatEscalation> getAll();

    ChatEscalation update(UUID id, ChatEscalationCommand command);

    void delete(UUID id);
}
