package com.mindconnect.infrastructure.priority.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.priority.model.aggregates.Priority;
import com.mindconnect.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de priorities.
 */
@Component
public class PriorityPersistenceMapper {

    public PriorityJpaEntity toEntity(Priority priority) {
        PriorityJpaEntity entity = new PriorityJpaEntity();
        entity.setId(priority.id());
        entity.setNamePriority(priority.namePriority());
        entity.setCreatedAt(priority.createdAt());
        entity.setUpdatedAt(priority.updatedAt());
        return entity;
    }

    public Priority toDomain(PriorityJpaEntity entity) {
        return new Priority(
                entity.getId(),
                entity.getNamePriority(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
