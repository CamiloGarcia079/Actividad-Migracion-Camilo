package com.mindconnect.infrastructure.clinicalrecordstatus.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.clinicalrecordstatus.model.aggregates.ClinicalRecordStatus;

/**
 * Respuesta JSON de la API para un registro de clinical_record_statuses.
 */
public record ClinicalRecordStatusResponse(
        UUID id,
        String code,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ClinicalRecordStatusResponse from(ClinicalRecordStatus clinicalRecordStatus) {
        return new ClinicalRecordStatusResponse(
                clinicalRecordStatus.id(), clinicalRecordStatus.code(), clinicalRecordStatus.name(), clinicalRecordStatus.createdAt(), clinicalRecordStatus.updatedAt());
    }
}
