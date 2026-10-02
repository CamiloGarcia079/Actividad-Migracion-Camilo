package com.mindconnect.domain.chatmessage.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_messages.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatMessage(
        UUID id,
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata,
        LocalDateTime createdAt) {

    public ChatMessage {
        DomainValidations.required(id, "id");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(messageTypeId, "messageTypeId");
        DomainValidations.required(participantId, "participantId");
        DomainValidations.required(content, "content");
        DomainValidations.required(metadata, "metadata");
        DomainValidations.required(createdAt, "createdAt");
    }
}
