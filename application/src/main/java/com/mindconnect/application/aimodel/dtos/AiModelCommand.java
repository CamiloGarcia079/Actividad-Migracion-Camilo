package com.mindconnect.application.aimodel.dtos;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de ai_models.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record AiModelCommand(
        UUID providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        Integer maxTokens,
        Integer contextWindow,
        Boolean isActive) {
}
