package com.mindconnect.application.diagnosticsystem.dtos;

/**
 * Datos que entran al caso de uso de diagnostic_systems.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record DiagnosticSystemCommand(
        String code,
        String name,
        Boolean active,
        String version) {
}
