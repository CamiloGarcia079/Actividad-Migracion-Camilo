package com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.encounterstatus.model.aggregates.EncounterStatus;
import com.mindconnect.domain.encounterstatus.repositories.EncounterStatusRepository;
import com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.mappers.EncounterStatusPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto EncounterStatusRepository del dominio usando JPA.
 */
@Component
public class EncounterStatusRepositoryAdapter implements EncounterStatusRepository {

    private final SpringDataEncounterStatusJpaRepository jpaRepository;
    private final EncounterStatusPersistenceMapper mapper;

    public EncounterStatusRepositoryAdapter(SpringDataEncounterStatusJpaRepository jpaRepository,
                                     EncounterStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterStatus save(EncounterStatus encounterStatus) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(encounterStatus)));
    }

    @Override
    public Optional<EncounterStatus> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EncounterStatus> findAll() {
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
