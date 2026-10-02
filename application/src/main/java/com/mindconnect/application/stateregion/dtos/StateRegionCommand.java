package com.mindconnect.application.stateregion.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de state_regions.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record StateRegionCommand(
        String nameRegion,
        String codeRegion,
        String description,
        Boolean isActive,
        UUID countryId) {
}
