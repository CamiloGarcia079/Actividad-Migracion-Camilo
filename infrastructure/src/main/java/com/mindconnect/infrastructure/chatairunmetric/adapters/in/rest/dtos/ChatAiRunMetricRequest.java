package com.mindconnect.infrastructure.chatairunmetric.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatairunmetric.dtos.ChatAiRunMetricCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_ai_run_metrics.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatAiRunMetricRequest(
        @NotNull UUID aiRunId,
        @NotNull Integer promptTokens,
        @NotNull Integer completionTokens,
        @NotNull Integer totalTokens,
        @NotNull BigDecimal cost) {

    public ChatAiRunMetricCommand toCommand() {
        return new ChatAiRunMetricCommand(
                aiRunId, promptTokens, completionTokens, totalTokens, cost);
    }
}
