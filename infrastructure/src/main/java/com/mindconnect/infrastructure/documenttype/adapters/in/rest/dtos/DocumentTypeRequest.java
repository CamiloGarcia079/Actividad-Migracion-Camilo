package com.mindconnect.infrastructure.documenttype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.documenttype.dtos.DocumentTypeCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de document_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record DocumentTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active) {

    public DocumentTypeCommand toCommand() {
        return new DocumentTypeCommand(
                code, name, active);
    }
}
