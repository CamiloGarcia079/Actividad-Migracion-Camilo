package com.mindconnect.infrastructure.encounter.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.encounter.dtos.EncounterCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de encounters.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record EncounterRequest(
        @NotNull UUID clinicalRecordId,
        @NotNull UUID professionalId,
        @NotNull UUID encounterTypeId,
        @NotNull LocalDateTime startedAt,
        @NotNull LocalDateTime endedAt,
        @NotBlank String reasonForVisit,
        @NotBlank String currentCondition,
        @NotNull UUID modalityId,
        @NotNull UUID statusId,
        @NotNull UUID createdBy,
        @NotNull UUID updatedBy) {

    public EncounterCommand toCommand() {
        return new EncounterCommand(
                clinicalRecordId, professionalId, encounterTypeId, startedAt, endedAt, reasonForVisit, currentCondition, modalityId, statusId, createdBy, updatedBy);
    }
}
