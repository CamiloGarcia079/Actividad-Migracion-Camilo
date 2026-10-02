package com.mindconnect.infrastructure.patient.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.patient.model.aggregates.Patient;
import com.mindconnect.domain.patient.repositories.PatientRepository;
import com.mindconnect.infrastructure.patient.adapters.out.persistence.mappers.PatientPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto PatientRepository del dominio usando JPA.
 */
@Component
public class PatientRepositoryAdapter implements PatientRepository {

    private final SpringDataPatientJpaRepository jpaRepository;
    private final PatientPersistenceMapper mapper;

    public PatientRepositoryAdapter(SpringDataPatientJpaRepository jpaRepository,
                                     PatientPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Patient save(Patient patient) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(patient)));
    }

    @Override
    public Optional<Patient> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Patient> findAll() {
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
