package com.mindconnect.infrastructure.risklevel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.risklevel.model.aggregates.RiskLevel;
import com.mindconnect.domain.risklevel.repositories.RiskLevelRepository;
import com.mindconnect.infrastructure.risklevel.adapters.out.persistence.mappers.RiskLevelPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto RiskLevelRepository del dominio usando JPA.
 */
@Component
public class RiskLevelRepositoryAdapter implements RiskLevelRepository {

    private final SpringDataRiskLevelJpaRepository jpaRepository;
    private final RiskLevelPersistenceMapper mapper;

    public RiskLevelRepositoryAdapter(SpringDataRiskLevelJpaRepository jpaRepository,
                                     RiskLevelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RiskLevel save(RiskLevel riskLevel) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(riskLevel)));
    }

    @Override
    public Optional<RiskLevel> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<RiskLevel> findAll() {
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
