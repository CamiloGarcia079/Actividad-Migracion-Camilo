package com.mindconnect.infrastructure.gender.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.gender.model.aggregates.Gender;
import com.mindconnect.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de genders.
 */
@Component
public class GenderPersistenceMapper {

    public GenderJpaEntity toEntity(Gender gender) {
        GenderJpaEntity entity = new GenderJpaEntity();
        entity.setId(gender.id());
        entity.setDescription(gender.description());
        entity.setCreatedAt(gender.createdAt());
        entity.setUpdatedAt(gender.updatedAt());
        return entity;
    }

    public Gender toDomain(GenderJpaEntity entity) {
        return new Gender(
                entity.getId(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
