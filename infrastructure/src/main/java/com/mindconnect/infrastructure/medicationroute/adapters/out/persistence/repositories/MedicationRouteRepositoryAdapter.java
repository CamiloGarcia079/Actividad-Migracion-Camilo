package com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.medicationroute.model.aggregates.MedicationRoute;
import com.mindconnect.domain.medicationroute.repositories.MedicationRouteRepository;
import com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.mappers.MedicationRoutePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto MedicationRouteRepository del dominio usando JPA.
 */
@Component
public class MedicationRouteRepositoryAdapter implements MedicationRouteRepository {

    private final SpringDataMedicationRouteJpaRepository jpaRepository;
    private final MedicationRoutePersistenceMapper mapper;

    public MedicationRouteRepositoryAdapter(SpringDataMedicationRouteJpaRepository jpaRepository,
                                     MedicationRoutePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MedicationRoute save(MedicationRoute medicationRoute) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(medicationRoute)));
    }

    @Override
    public Optional<MedicationRoute> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<MedicationRoute> findAll() {
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
