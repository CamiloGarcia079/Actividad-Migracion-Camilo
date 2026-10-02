package com.mindconnect.infrastructure.clinicalnote.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.clinicalnote.dtos.ClinicalNoteCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de clinical_notes.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ClinicalNoteRequest(
        @NotNull UUID encounterId,
        @NotNull UUID professionalId,
        @NotBlank String subjective,
        @NotBlank String objective,
        @NotBlank String assessment,
        @NotBlank String plan,
        @NotBlank String additionalNotes,
        @NotNull LocalDateTime signedAt) {

    public ClinicalNoteCommand toCommand() {
        return new ClinicalNoteCommand(
                encounterId, professionalId, subjective, objective, assessment, plan, additionalNotes, signedAt);
    }
}
