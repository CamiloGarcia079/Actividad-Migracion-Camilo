package com.mindconnect.infrastructure.diagnosticsystem.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.diagnosticsystem.dtos.DiagnosticSystemCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de diagnostic_systems.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record DiagnosticSystemRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active,
        @NotBlank @Size(max = 20) String version) {

    public DiagnosticSystemCommand toCommand() {
        return new DiagnosticSystemCommand(
                code, name, active, version);
    }
}
