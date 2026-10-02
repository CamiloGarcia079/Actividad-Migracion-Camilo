package com.mindconnect.infrastructure.encounter.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.encounter.model.aggregates.Encounter;
import com.mindconnect.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de encounters.
 */
@Component
public class EncounterPersistenceMapper {

    public EncounterJpaEntity toEntity(Encounter encounter) {
        EncounterJpaEntity entity = new EncounterJpaEntity();
        entity.setId(encounter.id());
        entity.setClinicalRecordId(encounter.clinicalRecordId());
        entity.setProfessionalId(encounter.professionalId());
        entity.setEncounterTypeId(encounter.encounterTypeId());
        entity.setStartedAt(encounter.startedAt());
        entity.setEndedAt(encounter.endedAt());
        entity.setReasonForVisit(encounter.reasonForVisit());
        entity.setCurrentCondition(encounter.currentCondition());
        entity.setModalityId(encounter.modalityId());
        entity.setStatusId(encounter.statusId());
        entity.setCreatedAt(encounter.createdAt());
        entity.setCreatedBy(encounter.createdBy());
        entity.setUpdatedAt(encounter.updatedAt());
        entity.setUpdatedBy(encounter.updatedBy());
        return entity;
    }

    public Encounter toDomain(EncounterJpaEntity entity) {
        return new Encounter(
                entity.getId(),
                entity.getClinicalRecordId(),
                entity.getProfessionalId(),
                entity.getEncounterTypeId(),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getReasonForVisit(),
                entity.getCurrentCondition(),
                entity.getModalityId(),
                entity.getStatusId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy());
    }
}
