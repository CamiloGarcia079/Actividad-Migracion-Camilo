package com.mindconnect.infrastructure.country.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.country.model.aggregates.Country;

/**
 * Respuesta JSON de la API para un registro de countries.
 */
public record CountryResponse(
        UUID id,
        String nameCountry,
        String codeCountry,
        String description,
        Boolean isActive,
        String telephonePrefix,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static CountryResponse from(Country country) {
        return new CountryResponse(
                country.id(), country.nameCountry(), country.codeCountry(), country.description(), country.isActive(), country.telephonePrefix(), country.createdAt(), country.updatedAt());
    }
}
