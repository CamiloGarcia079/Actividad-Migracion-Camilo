package com.mindconnect.application.mentalstatusexam.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de mental_status_exams.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record MentalStatusExamCommand(
        UUID encounterId,
        String appearance,
        String behavior,
        String attitude,
        String consciousness,
        String orientation,
        String attention,
        String memory,
        String speech,
        String mood,
        String affect,
        String thoughtProcess,
        String thoughtContent,
        String perception,
        String judgment,
        String insight,
        String psychomotorActivity,
        String observations,
        UUID createdBy) {
}
