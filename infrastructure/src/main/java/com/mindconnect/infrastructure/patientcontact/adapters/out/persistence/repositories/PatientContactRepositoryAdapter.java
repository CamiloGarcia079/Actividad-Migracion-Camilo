package com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.patientcontact.model.aggregates.PatientContact;
import com.mindconnect.domain.patientcontact.repositories.PatientContactRepository;
import com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.mappers.PatientContactPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto PatientContactRepository del dominio usando JPA.
 */
@Component
public class PatientContactRepositoryAdapter implements PatientContactRepository {

    private final SpringDataPatientContactJpaRepository jpaRepository;
    private final PatientContactPersistenceMapper mapper;

    public PatientContactRepositoryAdapter(SpringDataPatientContactJpaRepository jpaRepository,
                                     PatientContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientContact save(PatientContact patientContact) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(patientContact)));
    }

    @Override
    public Optional<PatientContact> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<PatientContact> findAll() {
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
