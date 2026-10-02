package com.mindconnect.domain.chatairunmetric.model.aggregates;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_ai_run_metrics.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatAiRunMetric(
        UUID id,
        UUID aiRunId,
        Integer promptTokens,
        Integer completionTokens,
        Integer totalTokens,
        BigDecimal cost,
        LocalDateTime createdAt) {

    public ChatAiRunMetric {
        DomainValidations.required(id, "id");
        DomainValidations.required(aiRunId, "aiRunId");
        DomainValidations.required(promptTokens, "promptTokens");
        DomainValidations.required(completionTokens, "completionTokens");
        DomainValidations.required(totalTokens, "totalTokens");
        DomainValidations.required(cost, "cost");
        DomainValidations.required(createdAt, "createdAt");
    }
}
