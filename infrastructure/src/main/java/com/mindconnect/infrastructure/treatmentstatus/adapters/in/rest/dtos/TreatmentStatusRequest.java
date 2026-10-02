package com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.treatmentstatus.dtos.TreatmentStatusCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de treatment_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record TreatmentStatusRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active) {

    public TreatmentStatusCommand toCommand() {
        return new TreatmentStatusCommand(
                code, name, active);
    }
}
