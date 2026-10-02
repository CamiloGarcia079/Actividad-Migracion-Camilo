package com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.treatmentstatus.model.aggregates.TreatmentStatus;
import com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.entity.TreatmentStatusJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de treatment_statuses.
 */
@Component
public class TreatmentStatusPersistenceMapper {

    public TreatmentStatusJpaEntity toEntity(TreatmentStatus treatmentStatus) {
        TreatmentStatusJpaEntity entity = new TreatmentStatusJpaEntity();
        entity.setId(treatmentStatus.id());
        entity.setCode(treatmentStatus.code());
        entity.setName(treatmentStatus.name());
        entity.setActive(treatmentStatus.active());
        entity.setCreatedAt(treatmentStatus.createdAt());
        entity.setUpdatedAt(treatmentStatus.updatedAt());
        return entity;
    }

    public TreatmentStatus toDomain(TreatmentStatusJpaEntity entity) {
        return new TreatmentStatus(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
