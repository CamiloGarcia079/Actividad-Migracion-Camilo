package com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.clinicalrecord.model.aggregates.ClinicalRecord;

/**
 * Respuesta JSON de la API para un registro de clinical_records.
 */
public record ClinicalRecordResponse(
        UUID id,
        UUID patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId,
        LocalDateTime createdAt,
        UUID createdBy) {

    public static ClinicalRecordResponse from(ClinicalRecord clinicalRecord) {
        return new ClinicalRecordResponse(
                clinicalRecord.id(), clinicalRecord.patientId(), clinicalRecord.creationDate(), clinicalRecord.recordNumber(), clinicalRecord.openedAt(), clinicalRecord.closedAt(), clinicalRecord.statusId(), clinicalRecord.createdAt(), clinicalRecord.createdBy());
    }
}
