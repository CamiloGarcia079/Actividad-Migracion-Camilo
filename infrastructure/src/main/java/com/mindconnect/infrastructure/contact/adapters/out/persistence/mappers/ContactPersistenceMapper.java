package com.mindconnect.infrastructure.contact.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.contact.model.aggregates.Contact;
import com.mindconnect.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de contacts.
 */
@Component
public class ContactPersistenceMapper {

    public ContactJpaEntity toEntity(Contact contact) {
        ContactJpaEntity entity = new ContactJpaEntity();
        entity.setId(contact.id());
        entity.setFullName(contact.fullName());
        entity.setEmail(contact.email());
        entity.setNotes(contact.notes());
        entity.setCityId(contact.cityId());
        entity.setCreatedAt(contact.createdAt());
        entity.setCreatedBy(contact.createdBy());
        entity.setUpdatedAt(contact.updatedAt());
        entity.setUpdatedBy(contact.updatedBy());
        return entity;
    }

    public Contact toDomain(ContactJpaEntity entity) {
        return new Contact(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getNotes(),
                entity.getCityId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy());
    }
}
