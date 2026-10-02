package com.mindconnect.application.country.dtos;

/**
 * Datos que entran al caso de uso de countries.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record CountryCommand(
        String nameCountry,
        String codeCountry,
        String description,
        Boolean isActive,
        String telephonePrefix) {
}
