package com.mindconnect.infrastructure.emailcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.emailcontact.dtos.EmailContactCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de email_contacts.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record EmailContactRequest(
        @NotNull UUID contactId,
        @NotBlank @Size(max = 150) String email,
        @NotBlank String notes) {

    public EmailContactCommand toCommand() {
        return new EmailContactCommand(
                contactId, email, notes);
    }
}
