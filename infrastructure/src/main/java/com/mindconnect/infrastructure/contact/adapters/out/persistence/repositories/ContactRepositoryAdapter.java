package com.mindconnect.infrastructure.contact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.contact.model.aggregates.Contact;
import com.mindconnect.domain.contact.repositories.ContactRepository;
import com.mindconnect.infrastructure.contact.adapters.out.persistence.mappers.ContactPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ContactRepository del dominio usando JPA.
 */
@Component
public class ContactRepositoryAdapter implements ContactRepository {

    private final SpringDataContactJpaRepository jpaRepository;
    private final ContactPersistenceMapper mapper;

    public ContactRepositoryAdapter(SpringDataContactJpaRepository jpaRepository,
                                     ContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Contact save(Contact contact) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(contact)));
    }

    @Override
    public Optional<Contact> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Contact> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
