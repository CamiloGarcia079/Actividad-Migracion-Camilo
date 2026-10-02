package com.mindconnect.infrastructure.encountermodality.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.encountermodality.dtos.EncounterModalityCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de encounter_modalities.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record EncounterModalityRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active) {

    public EncounterModalityCommand toCommand() {
        return new EncounterModalityCommand(
                code, name, active);
    }
}
