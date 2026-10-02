package com.mindconnect.infrastructure.patient.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.patient.model.aggregates.Patient;
import com.mindconnect.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de patients.
 */
@Component
public class PatientPersistenceMapper {

    public PatientJpaEntity toEntity(Patient patient) {
        PatientJpaEntity entity = new PatientJpaEntity();
        entity.setId(patient.id());
        entity.setDocumentTypeId(patient.documentTypeId());
        entity.setDocumentNumber(patient.documentNumber());
        entity.setFirstName(patient.firstName());
        entity.setMiddleName(patient.middleName());
        entity.setLastName(patient.lastName());
        entity.setSecondLastName(patient.secondLastName());
        entity.setBirthDate(patient.birthDate());
        entity.setBiologicalSexId(patient.biologicalSexId());
        entity.setGenderIdentity(patient.genderIdentity());
        entity.setEmail(patient.email());
        entity.setPhone(patient.phone());
        entity.setAddress(patient.address());
        entity.setActive(patient.active());
        entity.setCreatedAt(patient.createdAt());
        entity.setCreatedBy(patient.createdBy());
        entity.setUpdatedAt(patient.updatedAt());
        entity.setUpdatedBy(patient.updatedBy());
        entity.setCityId(patient.cityId());
        return entity;
    }

    public Patient toDomain(PatientJpaEntity entity) {
        return new Patient(
                entity.getId(),
                entity.getDocumentTypeId(),
                entity.getDocumentNumber(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getSecondLastName(),
                entity.getBirthDate(),
                entity.getBiologicalSexId(),
                entity.getGenderIdentity(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy(),
                entity.getCityId());
    }
}
