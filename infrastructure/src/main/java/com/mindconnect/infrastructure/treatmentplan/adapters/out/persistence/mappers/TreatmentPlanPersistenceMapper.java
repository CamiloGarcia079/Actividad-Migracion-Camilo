package com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.treatmentplan.model.aggregates.TreatmentPlan;
import com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de treatment_plans.
 */
@Component
public class TreatmentPlanPersistenceMapper {

    public TreatmentPlanJpaEntity toEntity(TreatmentPlan treatmentPlan) {
        TreatmentPlanJpaEntity entity = new TreatmentPlanJpaEntity();
        entity.setId(treatmentPlan.id());
        entity.setEncounterId(treatmentPlan.encounterId());
        entity.setProfessionalId(treatmentPlan.professionalId());
        entity.setTitle(treatmentPlan.title());
        entity.setDescription(treatmentPlan.description());
        entity.setStartDate(treatmentPlan.startDate());
        entity.setEndDate(treatmentPlan.endDate());
        entity.setTreatmentStatusId(treatmentPlan.treatmentStatusId());
        entity.setCreatedAt(treatmentPlan.createdAt());
        entity.setUpdatedAt(treatmentPlan.updatedAt());
        return entity;
    }

    public TreatmentPlan toDomain(TreatmentPlanJpaEntity entity) {
        return new TreatmentPlan(
                entity.getId(),
                entity.getEncounterId(),
                entity.getProfessionalId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getTreatmentStatusId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
