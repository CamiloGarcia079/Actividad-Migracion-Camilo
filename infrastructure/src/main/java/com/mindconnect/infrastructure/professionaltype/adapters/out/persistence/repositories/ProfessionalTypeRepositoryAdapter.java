package com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.professionaltype.model.aggregates.ProfessionalType;
import com.mindconnect.domain.professionaltype.repositories.ProfessionalTypeRepository;
import com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.mappers.ProfessionalTypePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ProfessionalTypeRepository del dominio usando JPA.
 */
@Component
public class ProfessionalTypeRepositoryAdapter implements ProfessionalTypeRepository {

    private final SpringDataProfessionalTypeJpaRepository jpaRepository;
    private final ProfessionalTypePersistenceMapper mapper;

    public ProfessionalTypeRepositoryAdapter(SpringDataProfessionalTypeJpaRepository jpaRepository,
                                     ProfessionalTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProfessionalType save(ProfessionalType professionalType) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(professionalType)));
    }

    @Override
    public Optional<ProfessionalType> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ProfessionalType> findAll() {
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
