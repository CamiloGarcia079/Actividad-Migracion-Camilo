package com.mindconnect.infrastructure.study.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.study.model.aggregates.Study;
import com.mindconnect.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de studies.
 */
@Component
public class StudyPersistenceMapper {

    public StudyJpaEntity toEntity(Study study) {
        StudyJpaEntity entity = new StudyJpaEntity();
        entity.setId(study.id());
        entity.setName(study.name());
        entity.setCreatedAt(study.createdAt());
        entity.setUpdatedAt(study.updatedAt());
        return entity;
    }

    public Study toDomain(StudyJpaEntity entity) {
        return new Study(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
