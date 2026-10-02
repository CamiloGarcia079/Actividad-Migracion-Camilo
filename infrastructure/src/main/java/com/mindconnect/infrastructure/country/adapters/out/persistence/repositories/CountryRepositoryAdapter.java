package com.mindconnect.infrastructure.country.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.country.model.aggregates.Country;
import com.mindconnect.domain.country.repositories.CountryRepository;
import com.mindconnect.infrastructure.country.adapters.out.persistence.mappers.CountryPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto CountryRepository del dominio usando JPA.
 */
@Component
public class CountryRepositoryAdapter implements CountryRepository {

    private final SpringDataCountryJpaRepository jpaRepository;
    private final CountryPersistenceMapper mapper;

    public CountryRepositoryAdapter(SpringDataCountryJpaRepository jpaRepository,
                                     CountryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Country save(Country country) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(country)));
    }

    @Override
    public Optional<Country> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Country> findAll() {
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
