package com.mindconnect.infrastructure.professional.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.professional.dtos.ProfessionalCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de professionals.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ProfessionalRequest(
        @NotNull UUID documentTypeId,
        @NotBlank @Size(max = 30) String documentNumber,
        @NotBlank @Size(max = 60) String firstName,
        @NotBlank @Size(max = 60) String lastName,
        @NotNull UUID professionalType,
        @NotBlank @Size(max = 100) String licenseNumber,
        @NotNull Boolean active,
        @NotNull UUID cityId) {

    public ProfessionalCommand toCommand() {
        return new ProfessionalCommand(
                documentTypeId, documentNumber, firstName, lastName, professionalType, licenseNumber, active, cityId);
    }
}
