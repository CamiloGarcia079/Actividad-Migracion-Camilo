package com.mindconnect.domain.chatescalation.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_escalations.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatEscalation(
        UUID id,
        UUID conversationId,
        UUID statusId,
        Boolean fromAi,
        String reason,
        LocalDateTime createdAt) {

    public ChatEscalation {
        DomainValidations.required(id, "id");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(statusId, "statusId");
        DomainValidations.required(fromAi, "fromAi");
        DomainValidations.required(reason, "reason");
        DomainValidations.required(createdAt, "createdAt");
    }
}
