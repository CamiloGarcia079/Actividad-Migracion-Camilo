package com.mindconnect.domain.chatairunerror.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_ai_run_errors.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatAiRunError(
        UUID id,
        UUID aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId,
        LocalDateTime createdAt) {

    public ChatAiRunError {
        DomainValidations.required(id, "id");
        DomainValidations.required(aiRunId, "aiRunId");
        DomainValidations.required(errorMessage, "errorMessage");
        DomainValidations.required(errorCode, "errorCode");
        DomainValidations.required(providerErrorId, "providerErrorId");
        DomainValidations.required(createdAt, "createdAt");
    }
}
