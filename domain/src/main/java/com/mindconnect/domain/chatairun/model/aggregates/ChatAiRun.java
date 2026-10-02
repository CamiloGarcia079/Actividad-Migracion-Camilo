package com.mindconnect.domain.chatairun.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_ai_runs.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatAiRun(
        UUID id,
        UUID conversationId,
        UUID messageId,
        UUID modelId,
        UUID aiRunStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ChatAiRun {
        DomainValidations.required(id, "id");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(messageId, "messageId");
        DomainValidations.required(modelId, "modelId");
        DomainValidations.required(aiRunStatusId, "aiRunStatusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
