package com.mindconnect.infrastructure.aimodel.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.aimodel.model.aggregates.AiModel;

/**
 * Respuesta JSON de la API para un registro de ai_models.
 */
public record AiModelResponse(
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

    public static AiModelResponse from(AiModel aiModel) {
        return new AiModelResponse(
                aiModel.id(), aiModel.providerModelId(), aiModel.nameModel(), aiModel.modelKey(), aiModel.inputTokenPrice(), aiModel.outputTokenPrice(), aiModel.maxTokens(), aiModel.contextWindow(), aiModel.isActive(), aiModel.createdAt(), aiModel.updatedAt());
    }
}
