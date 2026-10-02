package com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.encounterstatus.model.aggregates.EncounterStatus;
import com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de encounter_statuses.
 */
@Component
public class EncounterStatusPersistenceMapper {

    public EncounterStatusJpaEntity toEntity(EncounterStatus encounterStatus) {
        EncounterStatusJpaEntity entity = new EncounterStatusJpaEntity();
        entity.setId(encounterStatus.id());
        entity.setCode(encounterStatus.code());
        entity.setName(encounterStatus.name());
        entity.setActive(encounterStatus.active());
        entity.setCreatedAt(encounterStatus.createdAt());
        entity.setUpdatedAt(encounterStatus.updatedAt());
        return entity;
    }

    public EncounterStatus toDomain(EncounterStatusJpaEntity entity) {
        return new EncounterStatus(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
