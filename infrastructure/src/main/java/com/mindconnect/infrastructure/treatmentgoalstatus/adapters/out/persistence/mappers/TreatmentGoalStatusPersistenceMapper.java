package com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.treatmentgoalstatus.model.aggregates.TreatmentGoalStatus;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de treatment_goal_statuses.
 */
@Component
public class TreatmentGoalStatusPersistenceMapper {

    public TreatmentGoalStatusJpaEntity toEntity(TreatmentGoalStatus treatmentGoalStatus) {
        TreatmentGoalStatusJpaEntity entity = new TreatmentGoalStatusJpaEntity();
        entity.setId(treatmentGoalStatus.id());
        entity.setCode(treatmentGoalStatus.code());
        entity.setName(treatmentGoalStatus.name());
        entity.setActive(treatmentGoalStatus.active());
        entity.setCreatedAt(treatmentGoalStatus.createdAt());
        entity.setUpdatedAt(treatmentGoalStatus.updatedAt());
        return entity;
    }

    public TreatmentGoalStatus toDomain(TreatmentGoalStatusJpaEntity entity) {
        return new TreatmentGoalStatus(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
