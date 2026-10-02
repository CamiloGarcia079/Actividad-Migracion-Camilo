package com.mindconnect.infrastructure.riskassessment.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.riskassessment.model.aggregates.RiskAssessment;

/**
 * Respuesta JSON de la API para un registro de risk_assessments.
 */
public record RiskAssessmentResponse(
        UUID id,
        UUID encounterId,
        UUID riskLevelId,
        Boolean suicidalIdeation,
        Boolean suicidePlan,
        Boolean suicideIntent,
        Boolean selfHarm,
        Boolean harmToOthers,
        String riskFactors,
        String protectiveFactors,
        String clinicalActions,
        String observations,
        LocalDateTime assessedAt,
        UUID assessedBy) {

    public static RiskAssessmentResponse from(RiskAssessment riskAssessment) {
        return new RiskAssessmentResponse(
                riskAssessment.id(), riskAssessment.encounterId(), riskAssessment.riskLevelId(), riskAssessment.suicidalIdeation(), riskAssessment.suicidePlan(), riskAssessment.suicideIntent(), riskAssessment.selfHarm(), riskAssessment.harmToOthers(), riskAssessment.riskFactors(), riskAssessment.protectiveFactors(), riskAssessment.clinicalActions(), riskAssessment.observations(), riskAssessment.assessedAt(), riskAssessment.assessedBy());
    }
}
