package com.mindconnect.application.clinicalrecordstatus.dtos;

/**
 * Datos que entran al caso de uso de clinical_record_statuses.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ClinicalRecordStatusCommand(
        String code,
        String name) {
}
