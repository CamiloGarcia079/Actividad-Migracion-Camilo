package com.mindconnect.infrastructure.study.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.study.dtos.StudyCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de studies.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record StudyRequest(
        @NotBlank @Size(max = 40) String name) {

    public StudyCommand toCommand() {
        return new StudyCommand(
                name);
    }
}
