package com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.citymunicipality.model.aggregates.CityMunicipality;
import com.mindconnect.domain.citymunicipality.repositories.CityMunicipalityRepository;
import com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.mappers.CityMunicipalityPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto CityMunicipalityRepository del dominio usando JPA.
 */
@Component
public class CityMunicipalityRepositoryAdapter implements CityMunicipalityRepository {

    private final SpringDataCityMunicipalityJpaRepository jpaRepository;
    private final CityMunicipalityPersistenceMapper mapper;

    public CityMunicipalityRepositoryAdapter(SpringDataCityMunicipalityJpaRepository jpaRepository,
                                     CityMunicipalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CityMunicipality save(CityMunicipality cityMunicipality) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(cityMunicipality)));
    }

    @Override
    public Optional<CityMunicipality> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<CityMunicipality> findAll() {
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
