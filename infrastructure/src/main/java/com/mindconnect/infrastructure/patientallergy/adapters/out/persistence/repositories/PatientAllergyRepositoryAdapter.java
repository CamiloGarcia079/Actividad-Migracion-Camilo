package com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.patientallergy.model.aggregates.PatientAllergy;
import com.mindconnect.domain.patientallergy.repositories.PatientAllergyRepository;
import com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.mappers.PatientAllergyPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto PatientAllergyRepository del dominio usando JPA.
 */
@Component
public class PatientAllergyRepositoryAdapter implements PatientAllergyRepository {

    private final SpringDataPatientAllergyJpaRepository jpaRepository;
    private final PatientAllergyPersistenceMapper mapper;

    public PatientAllergyRepositoryAdapter(SpringDataPatientAllergyJpaRepository jpaRepository,
                                     PatientAllergyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PatientAllergy save(PatientAllergy patientAllergy) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(patientAllergy)));
    }

    @Override
    public Optional<PatientAllergy> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<PatientAllergy> findAll() {
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
