package com.mindconnect.infrastructure.mentalstatusexam.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.mentalstatusexam.dtos.MentalStatusExamCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de mental_status_exams.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record MentalStatusExamRequest(
        @NotNull UUID encounterId,
        @NotBlank String appearance,
        @NotBlank String behavior,
        @NotBlank String attitude,
        @NotBlank String consciousness,
        @NotBlank String orientation,
        @NotBlank String attention,
        @NotBlank String memory,
        @NotBlank String speech,
        @NotBlank String mood,
        @NotBlank String affect,
        @NotBlank String thoughtProcess,
        @NotBlank String thoughtContent,
        @NotBlank String perception,
        @NotBlank String judgment,
        @NotBlank String insight,
        @NotBlank String psychomotorActivity,
        @NotBlank String observations,
        @NotNull UUID createdBy) {

    public MentalStatusExamCommand toCommand() {
        return new MentalStatusExamCommand(
                encounterId, appearance, behavior, attitude, consciousness, orientation, attention, memory, speech, mood, affect, thoughtProcess, thoughtContent, perception, judgment, insight, psychomotorActivity, observations, createdBy);
    }
}
