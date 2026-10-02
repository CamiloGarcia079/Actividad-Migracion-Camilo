package com.mindconnect.infrastructure.patient.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.patient.dtos.PatientCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de patients.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record PatientRequest(
        @NotNull UUID documentTypeId,
        @NotBlank @Size(max = 30) String documentNumber,
        @NotBlank @Size(max = 50) String firstName,
        @Size(max = 50) String middleName,
        @NotBlank @Size(max = 50) String lastName,
        @Size(max = 50) String secondLastName,
        @NotNull LocalDate birthDate,
        @NotNull UUID biologicalSexId,
        @NotNull UUID genderIdentity,
        @NotBlank @Size(max = 150) String email,
        @NotBlank @Size(max = 30) String phone,
        @NotBlank @Size(max = 250) String address,
        @NotNull Boolean active,
        UUID createdBy,
        UUID updatedBy,
        @NotNull UUID cityId) {

    public PatientCommand toCommand() {
        return new PatientCommand(
                documentTypeId, documentNumber, firstName, middleName, lastName, secondLastName, birthDate, biologicalSexId, genderIdentity, email, phone, address, active, createdBy, updatedBy, cityId);
    }
}
