package com.mindconnect.infrastructure.contact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.contact.dtos.ContactCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de contacts.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ContactRequest(
        @NotBlank @Size(max = 200) String fullName,
        @NotBlank @Size(max = 150) String email,
        @NotBlank String notes,
        @NotNull UUID cityId,
        @NotNull UUID createdBy,
        UUID updatedBy) {

    public ContactCommand toCommand() {
        return new ContactCommand(
                fullName, email, notes, cityId, createdBy, updatedBy);
    }
}
