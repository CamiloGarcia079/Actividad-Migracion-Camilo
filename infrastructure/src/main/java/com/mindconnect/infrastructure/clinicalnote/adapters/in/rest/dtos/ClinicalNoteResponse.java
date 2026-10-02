package com.mindconnect.infrastructure.clinicalnote.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.clinicalnote.model.aggregates.ClinicalNote;

/**
 * Respuesta JSON de la API para un registro de clinical_notes.
 */
public record ClinicalNoteResponse(
        UUID id,
        UUID encounterId,
        UUID professionalId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ClinicalNoteResponse from(ClinicalNote clinicalNote) {
        return new ClinicalNoteResponse(
                clinicalNote.id(), clinicalNote.encounterId(), clinicalNote.professionalId(), clinicalNote.subjective(), clinicalNote.objective(), clinicalNote.assessment(), clinicalNote.plan(), clinicalNote.additionalNotes(), clinicalNote.signedAt(), clinicalNote.createdAt(), clinicalNote.updatedAt());
    }
}
