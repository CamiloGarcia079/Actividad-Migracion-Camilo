package com.mindconnect.domain.riskassessment.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla risk_assessments.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record RiskAssessment(
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

    public RiskAssessment {
        DomainValidations.required(id, "id");
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(riskLevelId, "riskLevelId");
        DomainValidations.required(suicidalIdeation, "suicidalIdeation");
        DomainValidations.required(suicidePlan, "suicidePlan");
        DomainValidations.required(suicideIntent, "suicideIntent");
        DomainValidations.required(selfHarm, "selfHarm");
        DomainValidations.required(harmToOthers, "harmToOthers");
        DomainValidations.required(riskFactors, "riskFactors");
        DomainValidations.required(protectiveFactors, "protectiveFactors");
        DomainValidations.required(clinicalActions, "clinicalActions");
        DomainValidations.required(observations, "observations");
        DomainValidations.required(assessedAt, "assessedAt");
        DomainValidations.required(assessedBy, "assessedBy");
    }
}
