package com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.treatmentgoal.model.aggregates.TreatmentGoal;
import com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de treatment_goals.
 */
@Component
public class TreatmentGoalPersistenceMapper {

    public TreatmentGoalJpaEntity toEntity(TreatmentGoal treatmentGoal) {
        TreatmentGoalJpaEntity entity = new TreatmentGoalJpaEntity();
        entity.setId(treatmentGoal.id());
        entity.setTreatmentPlanId(treatmentGoal.treatmentPlanId());
        entity.setDescription(treatmentGoal.description());
        entity.setTargetDate(treatmentGoal.targetDate());
        entity.setCompletedAt(treatmentGoal.completedAt());
        entity.setNotes(treatmentGoal.notes());
        entity.setTreatmentGoalId(treatmentGoal.treatmentGoalId());
        entity.setCreatedAt(treatmentGoal.createdAt());
        entity.setUpdatedAt(treatmentGoal.updatedAt());
        return entity;
    }

    public TreatmentGoal toDomain(TreatmentGoalJpaEntity entity) {
        return new TreatmentGoal(
                entity.getId(),
                entity.getTreatmentPlanId(),
                entity.getDescription(),
                entity.getTargetDate(),
                entity.getCompletedAt(),
                entity.getNotes(),
                entity.getTreatmentGoalId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
