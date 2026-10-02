package com.mindconnect.infrastructure.chatairunmetric.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatairunmetric.model.aggregates.ChatAiRunMetric;

/**
 * Respuesta JSON de la API para un registro de chat_ai_run_metrics.
 */
public record ChatAiRunMetricResponse(
        UUID id,
        UUID aiRunId,
        Integer promptTokens,
        Integer completionTokens,
        Integer totalTokens,
        BigDecimal cost,
        LocalDateTime createdAt) {

    public static ChatAiRunMetricResponse from(ChatAiRunMetric chatAiRunMetric) {
        return new ChatAiRunMetricResponse(
                chatAiRunMetric.id(), chatAiRunMetric.aiRunId(), chatAiRunMetric.promptTokens(), chatAiRunMetric.completionTokens(), chatAiRunMetric.totalTokens(), chatAiRunMetric.cost(), chatAiRunMetric.createdAt());
    }
}
