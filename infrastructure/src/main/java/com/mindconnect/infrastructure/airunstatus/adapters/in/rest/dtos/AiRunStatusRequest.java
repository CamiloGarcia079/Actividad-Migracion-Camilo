package com.mindconnect.infrastructure.airunstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.airunstatus.dtos.AiRunStatusCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de ai_runs_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record AiRunStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus) {

    public AiRunStatusCommand toCommand() {
        return new AiRunStatusCommand(
                nameStatus);
    }
}
