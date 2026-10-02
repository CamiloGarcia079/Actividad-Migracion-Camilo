package com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.riskassessment.model.aggregates.RiskAssessment;
import com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de risk_assessments.
 */
@Component
public class RiskAssessmentPersistenceMapper {

    public RiskAssessmentJpaEntity toEntity(RiskAssessment riskAssessment) {
        RiskAssessmentJpaEntity entity = new RiskAssessmentJpaEntity();
        entity.setId(riskAssessment.id());
        entity.setEncounterId(riskAssessment.encounterId());
        entity.setRiskLevelId(riskAssessment.riskLevelId());
        entity.setSuicidalIdeation(riskAssessment.suicidalIdeation());
        entity.setSuicidePlan(riskAssessment.suicidePlan());
        entity.setSuicideIntent(riskAssessment.suicideIntent());
        entity.setSelfHarm(riskAssessment.selfHarm());
        entity.setHarmToOthers(riskAssessment.harmToOthers());
        entity.setRiskFactors(riskAssessment.riskFactors());
        entity.setProtectiveFactors(riskAssessment.protectiveFactors());
        entity.setClinicalActions(riskAssessment.clinicalActions());
        entity.setObservations(riskAssessment.observations());
        entity.setAssessedAt(riskAssessment.assessedAt());
        entity.setAssessedBy(riskAssessment.assessedBy());
        return entity;
    }

    public RiskAssessment toDomain(RiskAssessmentJpaEntity entity) {
        return new RiskAssessment(
                entity.getId(),
                entity.getEncounterId(),
                entity.getRiskLevelId(),
                entity.getSuicidalIdeation(),
                entity.getSuicidePlan(),
                entity.getSuicideIntent(),
                entity.getSelfHarm(),
                entity.getHarmToOthers(),
                entity.getRiskFactors(),
                entity.getProtectiveFactors(),
                entity.getClinicalActions(),
                entity.getObservations(),
                entity.getAssessedAt(),
                entity.getAssessedBy());
    }
}
