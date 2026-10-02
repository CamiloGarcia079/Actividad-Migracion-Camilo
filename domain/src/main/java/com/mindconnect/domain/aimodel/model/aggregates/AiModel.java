package com.mindconnect.domain.aimodel.model.aggregates;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla ai_models.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record AiModel(
        UUID id,
        UUID providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public AiModel {
        DomainValidations.required(id, "id");
        DomainValidations.required(providerModelId, "providerModelId");
        DomainValidations.required(nameModel, "nameModel");
        DomainValidations.required(modelKey, "modelKey");
        DomainValidations.required(inputTokenPrice, "inputTokenPrice");
        DomainValidations.required(outputTokenPrice, "outputTokenPrice");
        DomainValidations.required(maxTokens, "maxTokens");
        DomainValidations.required(contextWindow, "contextWindow");
        DomainValidations.required(isActive, "isActive");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
