package com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.phonecontact.model.aggregates.PhoneContact;
import com.mindconnect.domain.phonecontact.repositories.PhoneContactRepository;
import com.mindconnect.infrastructure.phonecontact.adapters.out.persistence.mappers.PhoneContactPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto PhoneContactRepository del dominio usando JPA.
 */
@Component
public class PhoneContactRepositoryAdapter implements PhoneContactRepository {

    private final SpringDataPhoneContactJpaRepository jpaRepository;
    private final PhoneContactPersistenceMapper mapper;

    public PhoneContactRepositoryAdapter(SpringDataPhoneContactJpaRepository jpaRepository,
                                     PhoneContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PhoneContact save(PhoneContact phoneContact) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(phoneContact)));
    }

    @Override
    public Optional<PhoneContact> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<PhoneContact> findAll() {
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
