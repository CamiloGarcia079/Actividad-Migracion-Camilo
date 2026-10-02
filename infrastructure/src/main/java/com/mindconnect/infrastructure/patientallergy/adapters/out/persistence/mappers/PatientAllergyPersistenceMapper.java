package com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.patientallergy.model.aggregates.PatientAllergy;
import com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de patient_allergies.
 */
@Component
public class PatientAllergyPersistenceMapper {

    public PatientAllergyJpaEntity toEntity(PatientAllergy patientAllergy) {
        PatientAllergyJpaEntity entity = new PatientAllergyJpaEntity();
        entity.setId(patientAllergy.id());
        entity.setPatientId(patientAllergy.patientId());
        entity.setSubstance(patientAllergy.substance());
        entity.setReaction(patientAllergy.reaction());
        entity.setSeverity(patientAllergy.severity());
        entity.setActive(patientAllergy.active());
        entity.setRecordedAt(patientAllergy.recordedAt());
        entity.setRecordedBy(patientAllergy.recordedBy());
        entity.setCreatedAt(patientAllergy.createdAt());
        entity.setUpdatedAt(patientAllergy.updatedAt());
        return entity;
    }

    public PatientAllergy toDomain(PatientAllergyJpaEntity entity) {
        return new PatientAllergy(
                entity.getId(),
                entity.getPatientId(),
                entity.getSubstance(),
                entity.getReaction(),
                entity.getSeverity(),
                entity.getActive(),
                entity.getRecordedAt(),
                entity.getRecordedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
