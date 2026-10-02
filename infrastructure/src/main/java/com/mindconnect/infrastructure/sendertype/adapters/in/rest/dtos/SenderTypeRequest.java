package com.mindconnect.infrastructure.sendertype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.sendertype.dtos.SenderTypeCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de sender_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record SenderTypeRequest(
        @NotBlank @Size(max = 50) String nameType) {

    public SenderTypeCommand toCommand() {
        return new SenderTypeCommand(
                nameType);
    }
}
