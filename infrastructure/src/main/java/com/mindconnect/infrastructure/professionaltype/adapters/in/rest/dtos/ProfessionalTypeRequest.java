package com.mindconnect.infrastructure.professionaltype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.professionaltype.dtos.ProfessionalTypeCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de professional_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ProfessionalTypeRequest(
        @NotBlank @Size(max = 40) String name) {

    public ProfessionalTypeCommand toCommand() {
        return new ProfessionalTypeCommand(
                name);
    }
}
