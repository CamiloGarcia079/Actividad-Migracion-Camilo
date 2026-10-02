package com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.clinicalrecordstatus.model.aggregates.ClinicalRecordStatus;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.entity.ClinicalRecordStatusJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de clinical_record_statuses.
 */
@Component
public class ClinicalRecordStatusPersistenceMapper {

    public ClinicalRecordStatusJpaEntity toEntity(ClinicalRecordStatus clinicalRecordStatus) {
        ClinicalRecordStatusJpaEntity entity = new ClinicalRecordStatusJpaEntity();
        entity.setId(clinicalRecordStatus.id());
        entity.setCode(clinicalRecordStatus.code());
        entity.setName(clinicalRecordStatus.name());
        entity.setCreatedAt(clinicalRecordStatus.createdAt());
        entity.setUpdatedAt(clinicalRecordStatus.updatedAt());
        return entity;
    }

    public ClinicalRecordStatus toDomain(ClinicalRecordStatusJpaEntity entity) {
        return new ClinicalRecordStatus(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
