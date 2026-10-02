package com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.professionaltype.model.aggregates.ProfessionalType;
import com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de professional_types.
 */
@Component
public class ProfessionalTypePersistenceMapper {

    public ProfessionalTypeJpaEntity toEntity(ProfessionalType professionalType) {
        ProfessionalTypeJpaEntity entity = new ProfessionalTypeJpaEntity();
        entity.setId(professionalType.id());
        entity.setName(professionalType.name());
        entity.setCreatedAt(professionalType.createdAt());
        entity.setUpdatedAt(professionalType.updatedAt());
        return entity;
    }

    public ProfessionalType toDomain(ProfessionalTypeJpaEntity entity) {
        return new ProfessionalType(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
