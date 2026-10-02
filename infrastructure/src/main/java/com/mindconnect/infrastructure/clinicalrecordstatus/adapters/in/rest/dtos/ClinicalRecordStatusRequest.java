package com.mindconnect.infrastructure.clinicalrecordstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.clinicalrecordstatus.dtos.ClinicalRecordStatusCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de clinical_record_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ClinicalRecordStatusRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name) {

    public ClinicalRecordStatusCommand toCommand() {
        return new ClinicalRecordStatusCommand(
                code, name);
    }
}
