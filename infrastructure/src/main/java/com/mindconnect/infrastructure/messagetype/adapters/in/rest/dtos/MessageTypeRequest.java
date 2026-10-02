package com.mindconnect.infrastructure.messagetype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.messagetype.dtos.MessageTypeCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de message_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record MessageTypeRequest(
        @NotBlank @Size(max = 50) String nameType) {

    public MessageTypeCommand toCommand() {
        return new MessageTypeCommand(
                nameType);
    }
}
