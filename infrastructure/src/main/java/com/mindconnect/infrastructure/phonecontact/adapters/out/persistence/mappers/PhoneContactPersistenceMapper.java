package com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.phonecontact.model.aggregates.PhoneContact;
import com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de phone_contacts.
 */
@Component
public class PhoneContactPersistenceMapper {

    public PhoneContactJpaEntity toEntity(PhoneContact phoneContact) {
        PhoneContactJpaEntity entity = new PhoneContactJpaEntity();
        entity.setId(phoneContact.id());
        entity.setContactId(phoneContact.contactId());
        entity.setPhone(phoneContact.phone());
        entity.setNotes(phoneContact.notes());
        return entity;
    }

    public PhoneContact toDomain(PhoneContactJpaEntity entity) {
        return new PhoneContact(
                entity.getId(),
                entity.getContactId(),
                entity.getPhone(),
                entity.getNotes());
    }
}
