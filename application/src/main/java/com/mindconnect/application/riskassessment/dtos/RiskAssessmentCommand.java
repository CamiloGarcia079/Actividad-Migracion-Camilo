package com.mindconnect.application.riskassessment.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de risk_assessments.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record RiskAssessmentCommand(
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
}
