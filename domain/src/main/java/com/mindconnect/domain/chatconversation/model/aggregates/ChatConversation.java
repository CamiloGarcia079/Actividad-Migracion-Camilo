package com.mindconnect.domain.chatconversation.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_conversations.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatConversation(
        UUID id,
        UUID conversationStatusId,
        UUID priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ChatConversation {
        DomainValidations.required(id, "id");
        DomainValidations.required(conversationStatusId, "conversationStatusId");
        DomainValidations.required(priorityId, "priorityId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
