package com.mindconnect.infrastructure.gender.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.gender.dtos.GenderCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de genders.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record GenderRequest(
        @NotBlank @Size(max = 50) String description) {

    public GenderCommand toCommand() {
        return new GenderCommand(
                description);
    }
}
