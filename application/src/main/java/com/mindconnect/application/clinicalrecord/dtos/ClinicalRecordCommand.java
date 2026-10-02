package com.mindconnect.application.clinicalrecord.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de clinical_records.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ClinicalRecordCommand(
        UUID patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId,
        UUID createdBy) {
}
