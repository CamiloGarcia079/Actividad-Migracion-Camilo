package com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.encountermodality.model.aggregates.EncounterModality;
import com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de encounter_modalities.
 */
@Component
public class EncounterModalityPersistenceMapper {

    public EncounterModalityJpaEntity toEntity(EncounterModality encounterModality) {
        EncounterModalityJpaEntity entity = new EncounterModalityJpaEntity();
        entity.setId(encounterModality.id());
        entity.setCode(encounterModality.code());
        entity.setName(encounterModality.name());
        entity.setActive(encounterModality.active());
        entity.setCreatedAt(encounterModality.createdAt());
        entity.setUpdatedAt(encounterModality.updatedAt());
        return entity;
    }

    public EncounterModality toDomain(EncounterModalityJpaEntity entity) {
        return new EncounterModality(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
