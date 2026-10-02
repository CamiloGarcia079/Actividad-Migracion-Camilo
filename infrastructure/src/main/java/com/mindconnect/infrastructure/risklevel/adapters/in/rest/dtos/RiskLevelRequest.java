package com.mindconnect.infrastructure.risklevel.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.risklevel.dtos.RiskLevelCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de risk_levels.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record RiskLevelRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active,
        @NotNull Integer severity) {

    public RiskLevelCommand toCommand() {
        return new RiskLevelCommand(
                code, name, active, severity);
    }
}
