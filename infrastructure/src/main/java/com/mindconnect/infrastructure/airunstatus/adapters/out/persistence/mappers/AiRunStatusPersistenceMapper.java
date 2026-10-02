package com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.airunstatus.model.aggregates.AiRunStatus;
import com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de ai_runs_statuses.
 */
@Component
public class AiRunStatusPersistenceMapper {

    public AiRunStatusJpaEntity toEntity(AiRunStatus aiRunStatus) {
        AiRunStatusJpaEntity entity = new AiRunStatusJpaEntity();
        entity.setId(aiRunStatus.id());
        entity.setNameStatus(aiRunStatus.nameStatus());
        entity.setCreatedAt(aiRunStatus.createdAt());
        entity.setUpdatedAt(aiRunStatus.updatedAt());
        return entity;
    }

    public AiRunStatus toDomain(AiRunStatusJpaEntity entity) {
        return new AiRunStatus(
                entity.getId(),
                entity.getNameStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
