package com.mindconnect.domain.mentalstatusexam.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla mental_status_exams.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record MentalStatusExam(
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

    public MentalStatusExam {
        DomainValidations.required(id, "id");
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(appearance, "appearance");
        DomainValidations.required(behavior, "behavior");
        DomainValidations.required(attitude, "attitude");
        DomainValidations.required(consciousness, "consciousness");
        DomainValidations.required(orientation, "orientation");
        DomainValidations.required(attention, "attention");
        DomainValidations.required(memory, "memory");
        DomainValidations.required(speech, "speech");
        DomainValidations.required(mood, "mood");
        DomainValidations.required(affect, "affect");
        DomainValidations.required(thoughtProcess, "thoughtProcess");
        DomainValidations.required(thoughtContent, "thoughtContent");
        DomainValidations.required(perception, "perception");
        DomainValidations.required(judgment, "judgment");
        DomainValidations.required(insight, "insight");
        DomainValidations.required(psychomotorActivity, "psychomotorActivity");
        DomainValidations.required(observations, "observations");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(createdBy, "createdBy");
    }
}
