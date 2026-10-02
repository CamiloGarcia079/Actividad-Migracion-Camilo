package com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.clinicalrecord.dtos.ClinicalRecordCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de clinical_records.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ClinicalRecordRequest(
        @NotNull UUID patientId,
        @NotNull LocalDateTime creationDate,
        @NotBlank @Size(max = 50) String recordNumber,
        @NotNull LocalDateTime openedAt,
        @NotNull LocalDateTime closedAt,
        @NotNull UUID statusId,
        @NotNull UUID createdBy) {

    public ClinicalRecordCommand toCommand() {
        return new ClinicalRecordCommand(
                patientId, creationDate, recordNumber, openedAt, closedAt, statusId, createdBy);
    }
}
