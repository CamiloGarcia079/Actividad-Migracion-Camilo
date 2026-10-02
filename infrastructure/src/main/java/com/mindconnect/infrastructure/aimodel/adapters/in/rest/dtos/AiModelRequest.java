package com.mindconnect.infrastructure.aimodel.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.aimodel.dtos.AiModelCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de ai_models.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record AiModelRequest(
        @NotNull UUID providerModelId,
        @NotBlank @Size(max = 100) String nameModel,
        @NotBlank @Size(max = 120) String modelKey,
        @NotNull BigDecimal inputTokenPrice,
        @NotNull BigDecimal outputTokenPrice,
        @NotNull Integer maxTokens,
        @NotNull Integer contextWindow,
        @NotNull Boolean isActive) {

    public AiModelCommand toCommand() {
        return new AiModelCommand(
                providerModelId, nameModel, modelKey, inputTokenPrice, outputTokenPrice, maxTokens, contextWindow, isActive);
    }
}
