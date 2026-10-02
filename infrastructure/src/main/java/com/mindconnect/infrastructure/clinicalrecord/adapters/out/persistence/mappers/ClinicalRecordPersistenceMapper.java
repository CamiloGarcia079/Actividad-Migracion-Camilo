package com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.clinicalrecord.model.aggregates.ClinicalRecord;
import com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de clinical_records.
 */
@Component
public class ClinicalRecordPersistenceMapper {

    public ClinicalRecordJpaEntity toEntity(ClinicalRecord clinicalRecord) {
        ClinicalRecordJpaEntity entity = new ClinicalRecordJpaEntity();
        entity.setId(clinicalRecord.id());
        entity.setPatientId(clinicalRecord.patientId());
        entity.setCreationDate(clinicalRecord.creationDate());
        entity.setRecordNumber(clinicalRecord.recordNumber());
        entity.setOpenedAt(clinicalRecord.openedAt());
        entity.setClosedAt(clinicalRecord.closedAt());
        entity.setStatusId(clinicalRecord.statusId());
        entity.setCreatedAt(clinicalRecord.createdAt());
        entity.setCreatedBy(clinicalRecord.createdBy());
        return entity;
    }

    public ClinicalRecord toDomain(ClinicalRecordJpaEntity entity) {
        return new ClinicalRecord(
                entity.getId(),
                entity.getPatientId(),
                entity.getCreationDate(),
                entity.getRecordNumber(),
                entity.getOpenedAt(),
                entity.getClosedAt(),
                entity.getStatusId(),
                entity.getCreatedAt(),
                entity.getCreatedBy());
    }
}
