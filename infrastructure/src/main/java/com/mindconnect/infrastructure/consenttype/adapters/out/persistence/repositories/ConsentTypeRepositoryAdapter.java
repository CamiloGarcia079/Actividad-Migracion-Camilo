package com.mindconnect.infrastructure.consenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.consenttype.model.aggregates.ConsentType;
import com.mindconnect.domain.consenttype.repositories.ConsentTypeRepository;
import com.mindconnect.infrastructure.consenttype.adapters.out.persistence.mappers.ConsentTypePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ConsentTypeRepository del dominio usando JPA.
 */
@Component
public class ConsentTypeRepositoryAdapter implements ConsentTypeRepository {

    private final SpringDataConsentTypeJpaRepository jpaRepository;
    private final ConsentTypePersistenceMapper mapper;

    public ConsentTypeRepositoryAdapter(SpringDataConsentTypeJpaRepository jpaRepository,
                                     ConsentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ConsentType save(ConsentType consentType) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(consentType)));
    }

    @Override
    public Optional<ConsentType> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ConsentType> findAll() {
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
