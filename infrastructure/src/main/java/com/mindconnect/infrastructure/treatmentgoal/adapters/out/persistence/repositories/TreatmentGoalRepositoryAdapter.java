package com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.treatmentgoal.model.aggregates.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.repositories.TreatmentGoalRepository;
import com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.mappers.TreatmentGoalPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto TreatmentGoalRepository del dominio usando JPA.
 */
@Component
public class TreatmentGoalRepositoryAdapter implements TreatmentGoalRepository {

    private final SpringDataTreatmentGoalJpaRepository jpaRepository;
    private final TreatmentGoalPersistenceMapper mapper;

    public TreatmentGoalRepositoryAdapter(SpringDataTreatmentGoalJpaRepository jpaRepository,
                                     TreatmentGoalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentGoal save(TreatmentGoal treatmentGoal) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(treatmentGoal)));
    }

    @Override
    public Optional<TreatmentGoal> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentGoal> findAll() {
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
