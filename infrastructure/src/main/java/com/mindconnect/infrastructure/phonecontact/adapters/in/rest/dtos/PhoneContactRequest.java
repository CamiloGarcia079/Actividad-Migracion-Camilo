package com.mindconnect.infrastructure.phonecontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.phonecontact.dtos.PhoneContactCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de phone_contacts.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record PhoneContactRequest(
        @NotNull UUID contactId,
        @Size(max = 30) String phone,
        @NotBlank String notes) {

    public PhoneContactCommand toCommand() {
        return new PhoneContactCommand(
                contactId, phone, notes);
    }
}
