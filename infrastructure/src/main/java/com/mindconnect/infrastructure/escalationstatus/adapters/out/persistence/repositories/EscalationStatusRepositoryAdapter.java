package com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.escalationstatus.model.aggregates.EscalationStatus;
import com.mindconnect.domain.escalationstatus.repositories.EscalationStatusRepository;
import com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.mappers.EscalationStatusPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto EscalationStatusRepository del dominio usando JPA.
 */
@Component
public class EscalationStatusRepositoryAdapter implements EscalationStatusRepository {

    private final SpringDataEscalationStatusJpaRepository jpaRepository;
    private final EscalationStatusPersistenceMapper mapper;

    public EscalationStatusRepositoryAdapter(SpringDataEscalationStatusJpaRepository jpaRepository,
                                     EscalationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EscalationStatus save(EscalationStatus escalationStatus) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(escalationStatus)));
    }

    @Override
    public Optional<EscalationStatus> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EscalationStatus> findAll() {
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
