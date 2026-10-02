package com.mindconnect.infrastructure.riskassessment.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.riskassessment.dtos.RiskAssessmentCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de risk_assessments.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record RiskAssessmentRequest(
        @NotNull UUID encounterId,
        @NotNull UUID riskLevelId,
        @NotNull Boolean suicidalIdeation,
        @NotNull Boolean suicidePlan,
        @NotNull Boolean suicideIntent,
        @NotNull Boolean selfHarm,
        @NotNull Boolean harmToOthers,
        @NotBlank String riskFactors,
        @NotBlank String protectiveFactors,
        @NotBlank String clinicalActions,
        @NotBlank String observations,
        @NotNull LocalDateTime assessedAt,
        @NotNull UUID assessedBy) {

    public RiskAssessmentCommand toCommand() {
        return new RiskAssessmentCommand(
                encounterId, riskLevelId, suicidalIdeation, suicidePlan, suicideIntent, selfHarm, harmToOthers, riskFactors, protectiveFactors, clinicalActions, observations, assessedAt, assessedBy);
    }
}
