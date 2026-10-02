package com.mindconnect.infrastructure.citymunicipality.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.citymunicipality.model.aggregates.CityMunicipality;

/**
 * Respuesta JSON de la API para un registro de city_municipalities.
 */
public record CityMunicipalityResponse(
        UUID id,
        String nameCity,
        String codeCity,
        String description,
        Boolean isActive,
        UUID regionId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static CityMunicipalityResponse from(CityMunicipality cityMunicipality) {
        return new CityMunicipalityResponse(
                cityMunicipality.id(), cityMunicipality.nameCity(), cityMunicipality.codeCity(), cityMunicipality.description(), cityMunicipality.isActive(), cityMunicipality.regionId(), cityMunicipality.createdAt(), cityMunicipality.updatedAt());
    }
}
