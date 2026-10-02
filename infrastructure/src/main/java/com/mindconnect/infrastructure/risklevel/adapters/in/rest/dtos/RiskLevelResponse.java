package com.mindconnect.infrastructure.risklevel.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.risklevel.model.aggregates.RiskLevel;

/**
 * Respuesta JSON de la API para un registro de risk_levels.
 */
public record RiskLevelResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        Integer severity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static RiskLevelResponse from(RiskLevel riskLevel) {
        return new RiskLevelResponse(
                riskLevel.id(), riskLevel.code(), riskLevel.name(), riskLevel.active(), riskLevel.severity(), riskLevel.createdAt(), riskLevel.updatedAt());
    }
}
