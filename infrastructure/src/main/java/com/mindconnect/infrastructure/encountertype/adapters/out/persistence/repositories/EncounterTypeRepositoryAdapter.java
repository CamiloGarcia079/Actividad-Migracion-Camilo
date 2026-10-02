package com.mindconnect.infrastructure.encountertype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.encountertype.model.aggregates.EncounterType;
import com.mindconnect.domain.encountertype.repositories.EncounterTypeRepository;
import com.mindconnect.infrastructure.encountertype.adapters.out.persistence.mappers.EncounterTypePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto EncounterTypeRepository del dominio usando JPA.
 */
@Component
public class EncounterTypeRepositoryAdapter implements EncounterTypeRepository {

    private final SpringDataEncounterTypeJpaRepository jpaRepository;
    private final EncounterTypePersistenceMapper mapper;

    public EncounterTypeRepositoryAdapter(SpringDataEncounterTypeJpaRepository jpaRepository,
                                     EncounterTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterType save(EncounterType encounterType) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(encounterType)));
    }

    @Override
    public Optional<EncounterType> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EncounterType> findAll() {
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
