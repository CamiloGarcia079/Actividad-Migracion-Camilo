package com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.assessmenttype.model.aggregates.AssessmentType;
import com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de assessment_types.
 */
@Component
public class AssessmentTypePersistenceMapper {

    public AssessmentTypeJpaEntity toEntity(AssessmentType assessmentType) {
        AssessmentTypeJpaEntity entity = new AssessmentTypeJpaEntity();
        entity.setId(assessmentType.id());
        entity.setCode(assessmentType.code());
        entity.setName(assessmentType.name());
        entity.setActive(assessmentType.active());
        entity.setDescription(assessmentType.description());
        entity.setCreatedAt(assessmentType.createdAt());
        entity.setUpdatedAt(assessmentType.updatedAt());
        return entity;
    }

    public AssessmentType toDomain(AssessmentTypeJpaEntity entity) {
        return new AssessmentType(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
