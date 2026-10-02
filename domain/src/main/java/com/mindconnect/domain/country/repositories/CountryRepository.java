package com.mindconnect.domain.country.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.country.model.aggregates.Country;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar countries.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface CountryRepository {

    Country save(Country country);

    Optional<Country> findById(UUID id);

    List<Country> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
