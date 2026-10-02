package com.mindconnect.infrastructure.mentalstatusexam.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.mentalstatusexam.model.aggregates.MentalStatusExam;

/**
 * Respuesta JSON de la API para un registro de mental_status_exams.
 */
public record MentalStatusExamResponse(
        UUID id,
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
        LocalDateTime createdAt,
        UUID createdBy) {

    public static MentalStatusExamResponse from(MentalStatusExam mentalStatusExam) {
        return new MentalStatusExamResponse(
                mentalStatusExam.id(), mentalStatusExam.encounterId(), mentalStatusExam.appearance(), mentalStatusExam.behavior(), mentalStatusExam.attitude(), mentalStatusExam.consciousness(), mentalStatusExam.orientation(), mentalStatusExam.attention(), mentalStatusExam.memory(), mentalStatusExam.speech(), mentalStatusExam.mood(), mentalStatusExam.affect(), mentalStatusExam.thoughtProcess(), mentalStatusExam.thoughtContent(), mentalStatusExam.perception(), mentalStatusExam.judgment(), mentalStatusExam.insight(), mentalStatusExam.psychomotorActivity(), mentalStatusExam.observations(), mentalStatusExam.createdAt(), mentalStatusExam.createdBy());
    }
}
