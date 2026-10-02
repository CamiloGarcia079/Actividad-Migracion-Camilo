package com.mindconnect.infrastructure.consenttype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.consenttype.dtos.ConsentTypeCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de consent_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ConsentTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active,
        @NotBlank String description) {

    public ConsentTypeCommand toCommand() {
        return new ConsentTypeCommand(
                code, name, active, description);
    }
}
