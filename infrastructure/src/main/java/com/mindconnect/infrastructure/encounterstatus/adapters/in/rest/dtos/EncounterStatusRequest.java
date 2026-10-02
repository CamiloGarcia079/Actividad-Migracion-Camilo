package com.mindconnect.infrastructure.encounterstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.encounterstatus.dtos.EncounterStatusCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de encounter_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record EncounterStatusRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active) {

    public EncounterStatusCommand toCommand() {
        return new EncounterStatusCommand(
                code, name, active);
    }
}
