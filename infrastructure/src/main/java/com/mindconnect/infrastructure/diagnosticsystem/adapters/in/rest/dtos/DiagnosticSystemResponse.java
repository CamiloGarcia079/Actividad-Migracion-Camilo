package com.mindconnect.infrastructure.diagnosticsystem.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.diagnosticsystem.model.aggregates.DiagnosticSystem;

/**
 * Respuesta JSON de la API para un registro de diagnostic_systems.
 */
public record DiagnosticSystemResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        String version,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static DiagnosticSystemResponse from(DiagnosticSystem diagnosticSystem) {
        return new DiagnosticSystemResponse(
                diagnosticSystem.id(), diagnosticSystem.code(), diagnosticSystem.name(), diagnosticSystem.active(), diagnosticSystem.version(), diagnosticSystem.createdAt(), diagnosticSystem.updatedAt());
    }
}
