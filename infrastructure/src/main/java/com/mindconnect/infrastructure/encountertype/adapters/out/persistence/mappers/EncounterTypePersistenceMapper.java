package com.mindconnect.infrastructure.encountertype.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.encountertype.model.aggregates.EncounterType;
import com.mindconnect.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de encounter_types.
 */
@Component
public class EncounterTypePersistenceMapper {

    public EncounterTypeJpaEntity toEntity(EncounterType encounterType) {
        EncounterTypeJpaEntity entity = new EncounterTypeJpaEntity();
        entity.setId(encounterType.id());
        entity.setCode(encounterType.code());
        entity.setName(encounterType.name());
        entity.setActive(encounterType.active());
        entity.setCreatedAt(encounterType.createdAt());
        entity.setUpdatedAt(encounterType.updatedAt());
        return entity;
    }

    public EncounterType toDomain(EncounterTypeJpaEntity entity) {
        return new EncounterType(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
