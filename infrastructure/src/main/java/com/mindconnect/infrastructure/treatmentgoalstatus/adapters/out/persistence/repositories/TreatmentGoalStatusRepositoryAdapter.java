package com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.treatmentgoalstatus.model.aggregates.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.repositories.TreatmentGoalStatusRepository;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers.TreatmentGoalStatusPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto TreatmentGoalStatusRepository del dominio usando JPA.
 */
@Component
public class TreatmentGoalStatusRepositoryAdapter implements TreatmentGoalStatusRepository {

    private final SpringDataTreatmentGoalStatusJpaRepository jpaRepository;
    private final TreatmentGoalStatusPersistenceMapper mapper;

    public TreatmentGoalStatusRepositoryAdapter(SpringDataTreatmentGoalStatusJpaRepository jpaRepository,
                                     TreatmentGoalStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentGoalStatus save(TreatmentGoalStatus treatmentGoalStatus) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(treatmentGoalStatus)));
    }

    @Override
    public Optional<TreatmentGoalStatus> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentGoalStatus> findAll() {
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
