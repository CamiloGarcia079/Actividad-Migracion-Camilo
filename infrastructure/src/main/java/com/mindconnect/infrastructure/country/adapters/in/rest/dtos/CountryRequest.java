package com.mindconnect.infrastructure.country.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.country.dtos.CountryCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de countries.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CountryRequest(
        @NotBlank @Size(max = 50) String nameCountry,
        @NotBlank @Size(max = 10) String codeCountry,
        @NotBlank @Size(max = 100) String description,
        @NotNull Boolean isActive,
        @NotBlank @Size(max = 5) String telephonePrefix) {

    public CountryCommand toCommand() {
        return new CountryCommand(
                nameCountry, codeCountry, description, isActive, telephonePrefix);
    }
}
