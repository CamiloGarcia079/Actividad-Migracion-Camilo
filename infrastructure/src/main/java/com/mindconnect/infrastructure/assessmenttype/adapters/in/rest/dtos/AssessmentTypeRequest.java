package com.mindconnect.infrastructure.assessmenttype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.assessmenttype.dtos.AssessmentTypeCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de assessment_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record AssessmentTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active,
        @NotBlank String description) {

    public AssessmentTypeCommand toCommand() {
        return new AssessmentTypeCommand(
                code, name, active, description);
    }
}
