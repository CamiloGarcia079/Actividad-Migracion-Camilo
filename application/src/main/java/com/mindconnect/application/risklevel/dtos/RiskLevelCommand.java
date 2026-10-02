package com.mindconnect.application.risklevel.dtos;

/**
 * Datos que entran al caso de uso de risk_levels.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record RiskLevelCommand(
        String code,
        String name,
        Boolean active,
        Integer severity) {
}
