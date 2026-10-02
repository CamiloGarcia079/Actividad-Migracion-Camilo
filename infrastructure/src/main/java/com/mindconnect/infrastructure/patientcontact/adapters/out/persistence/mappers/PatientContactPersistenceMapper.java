package com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.patientcontact.model.aggregates.PatientContact;
import com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de patient_contacts.
 */
@Component
public class PatientContactPersistenceMapper {

    public PatientContactJpaEntity toEntity(PatientContact patientContact) {
        PatientContactJpaEntity entity = new PatientContactJpaEntity();
        entity.setId(patientContact.id());
        entity.setContactId(patientContact.contactId());
        entity.setPatientId(patientContact.patientId());
        entity.setIsPrimaryContact(patientContact.isPrimaryContact());
        entity.setIsEmergencyContact(patientContact.isEmergencyContact());
        entity.setRelationshipTypeId(patientContact.relationshipTypeId());
        return entity;
    }

    public PatientContact toDomain(PatientContactJpaEntity entity) {
        return new PatientContact(
                entity.getId(),
                entity.getContactId(),
                entity.getPatientId(),
                entity.getIsPrimaryContact(),
                entity.getIsEmergencyContact(),
                entity.getRelationshipTypeId());
    }
}
