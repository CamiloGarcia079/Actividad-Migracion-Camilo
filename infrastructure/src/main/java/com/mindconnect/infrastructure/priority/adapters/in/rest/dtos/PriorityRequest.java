package com.mindconnect.infrastructure.priority.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.priority.dtos.PriorityCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de priorities.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record PriorityRequest(
        @NotBlank @Size(max = 50) String namePriority) {

    public PriorityCommand toCommand() {
        return new PriorityCommand(
                namePriority);
    }
}
