package com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.diagnosticsystem.model.aggregates.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.repositories.DiagnosticSystemRepository;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.mappers.DiagnosticSystemPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto DiagnosticSystemRepository del dominio usando JPA.
 */
@Component
public class DiagnosticSystemRepositoryAdapter implements DiagnosticSystemRepository {

    private final SpringDataDiagnosticSystemJpaRepository jpaRepository;
    private final DiagnosticSystemPersistenceMapper mapper;

    public DiagnosticSystemRepositoryAdapter(SpringDataDiagnosticSystemJpaRepository jpaRepository,
                                     DiagnosticSystemPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DiagnosticSystem save(DiagnosticSystem diagnosticSystem) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(diagnosticSystem)));
    }

    @Override
    public Optional<DiagnosticSystem> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DiagnosticSystem> findAll() {
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
