package com.mindconnect.infrastructure.escalationstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.escalationstatus.dtos.EscalationStatusCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de escalations_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record EscalationStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus) {

    public EscalationStatusCommand toCommand() {
        return new EscalationStatusCommand(
                nameStatus);
    }
}
