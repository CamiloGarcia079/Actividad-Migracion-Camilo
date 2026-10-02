package com.mindconnect.infrastructure.encountertype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.encountertype.dtos.EncounterTypeCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de encounter_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record EncounterTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active) {

    public EncounterTypeCommand toCommand() {
        return new EncounterTypeCommand(
                code, name, active);
    }
}
