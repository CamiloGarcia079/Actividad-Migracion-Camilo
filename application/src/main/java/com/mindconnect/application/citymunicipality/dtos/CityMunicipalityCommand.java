package com.mindconnect.application.citymunicipality.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de city_municipalities.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record CityMunicipalityCommand(
        String nameCity,
        String codeCity,
        String description,
        Boolean isActive,
        UUID regionId) {
}
