package com.mindconnect.infrastructure.stateregion.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.stateregion.dtos.StateRegionCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de state_regions.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record StateRegionRequest(
        @NotBlank @Size(max = 50) String nameRegion,
        @NotBlank @Size(max = 10) String codeRegion,
        @NotBlank @Size(max = 100) String description,
        @NotNull Boolean isActive,
        @NotNull UUID countryId) {

    public StateRegionCommand toCommand() {
        return new StateRegionCommand(
                nameRegion, codeRegion, description, isActive, countryId);
    }
}
