package com.mindconnect.infrastructure.conversationstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.conversationstatus.dtos.ConversationStatusCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de conversations_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ConversationStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus) {

    public ConversationStatusCommand toCommand() {
        return new ConversationStatusCommand(
                nameStatus);
    }
}
