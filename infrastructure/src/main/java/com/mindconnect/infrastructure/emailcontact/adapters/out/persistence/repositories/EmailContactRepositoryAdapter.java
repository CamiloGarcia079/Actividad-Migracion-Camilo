package com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.emailcontact.model.aggregates.EmailContact;
import com.mindconnect.domain.emailcontact.repositories.EmailContactRepository;
import com.mindconnect.infrastructure.emailcontact.adapters.out.persistence.mappers.EmailContactPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto EmailContactRepository del dominio usando JPA.
 */
@Component
public class EmailContactRepositoryAdapter implements EmailContactRepository {

    private final SpringDataEmailContactJpaRepository jpaRepository;
    private final EmailContactPersistenceMapper mapper;

    public EmailContactRepositoryAdapter(SpringDataEmailContactJpaRepository jpaRepository,
                                     EmailContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EmailContact save(EmailContact emailContact) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(emailContact)));
    }

    @Override
    public Optional<EmailContact> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EmailContact> findAll() {
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
