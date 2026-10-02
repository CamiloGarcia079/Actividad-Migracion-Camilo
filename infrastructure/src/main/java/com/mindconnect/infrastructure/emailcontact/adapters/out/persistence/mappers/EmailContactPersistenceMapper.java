package com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.emailcontact.model.aggregates.EmailContact;
import com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de email_contacts.
 */
@Component
public class EmailContactPersistenceMapper {

    public EmailContactJpaEntity toEntity(EmailContact emailContact) {
        EmailContactJpaEntity entity = new EmailContactJpaEntity();
        entity.setId(emailContact.id());
        entity.setContactId(emailContact.contactId());
        entity.setEmail(emailContact.email());
        entity.setNotes(emailContact.notes());
        entity.setCreatedAt(emailContact.createdAt());
        entity.setUpdatedAt(emailContact.updatedAt());
        return entity;
    }

    public EmailContact toDomain(EmailContactJpaEntity entity) {
        return new EmailContact(
                entity.getId(),
                entity.getContactId(),
                entity.getEmail(),
                entity.getNotes(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
