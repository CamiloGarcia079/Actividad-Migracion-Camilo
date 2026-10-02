package com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.escalationstatus.model.aggregates.EscalationStatus;
import com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de escalations_statuses.
 */
@Component
public class EscalationStatusPersistenceMapper {

    public EscalationStatusJpaEntity toEntity(EscalationStatus escalationStatus) {
        EscalationStatusJpaEntity entity = new EscalationStatusJpaEntity();
        entity.setId(escalationStatus.id());
        entity.setNameStatus(escalationStatus.nameStatus());
        entity.setCreatedAt(escalationStatus.createdAt());
        entity.setUpdatedAt(escalationStatus.updatedAt());
        return entity;
    }

    public EscalationStatus toDomain(EscalationStatusJpaEntity entity) {
        return new EscalationStatus(
                entity.getId(),
                entity.getNameStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
