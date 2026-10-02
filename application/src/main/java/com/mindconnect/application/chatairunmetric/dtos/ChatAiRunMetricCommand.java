package com.mindconnect.application.chatairunmetric.dtos;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_ai_run_metrics.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatAiRunMetricCommand(
        UUID aiRunId,
        Integer promptTokens,
        Integer completionTokens,
        Integer totalTokens,
        BigDecimal cost) {
}
