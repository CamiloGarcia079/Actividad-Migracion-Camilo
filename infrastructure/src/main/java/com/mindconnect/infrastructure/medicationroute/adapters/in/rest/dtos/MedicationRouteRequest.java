package com.mindconnect.infrastructure.medicationroute.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.medicationroute.dtos.MedicationRouteCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de medication_routes.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record MedicationRouteRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active) {

    public MedicationRouteCommand toCommand() {
        return new MedicationRouteCommand(
                code, name, active);
    }
}
