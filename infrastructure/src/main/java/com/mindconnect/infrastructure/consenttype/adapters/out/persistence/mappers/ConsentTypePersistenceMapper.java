package com.mindconnect.infrastructure.consenttype.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.consenttype.model.aggregates.ConsentType;
import com.mindconnect.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de consent_types.
 */
@Component
public class ConsentTypePersistenceMapper {

    public ConsentTypeJpaEntity toEntity(ConsentType consentType) {
        ConsentTypeJpaEntity entity = new ConsentTypeJpaEntity();
        entity.setId(consentType.id());
        entity.setCode(consentType.code());
        entity.setName(consentType.name());
        entity.setActive(consentType.active());
        entity.setDescription(consentType.description());
        entity.setCreatedAt(consentType.createdAt());
        entity.setUpdatedAt(consentType.updatedAt());
        return entity;
    }

    public ConsentType toDomain(ConsentTypeJpaEntity entity) {
        return new ConsentType(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
