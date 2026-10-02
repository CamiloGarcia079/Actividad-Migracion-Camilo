package com.mindconnect.domain.citymunicipality.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.citymunicipality.model.aggregates.CityMunicipality;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar city_municipalities.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface CityMunicipalityRepository {

    CityMunicipality save(CityMunicipality cityMunicipality);

    Optional<CityMunicipality> findById(UUID id);

    List<CityMunicipality> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
