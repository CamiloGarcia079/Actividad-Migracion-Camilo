package com.mindconnect.infrastructure.citymunicipality.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.citymunicipality.dtos.CityMunicipalityCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de city_municipalities.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CityMunicipalityRequest(
        @NotBlank @Size(max = 50) String nameCity,
        @NotBlank @Size(max = 10) String codeCity,
        @NotBlank @Size(max = 100) String description,
        @NotNull Boolean isActive,
        @NotNull UUID regionId) {

    public CityMunicipalityCommand toCommand() {
        return new CityMunicipalityCommand(
                nameCity, codeCity, description, isActive, regionId);
    }
}
