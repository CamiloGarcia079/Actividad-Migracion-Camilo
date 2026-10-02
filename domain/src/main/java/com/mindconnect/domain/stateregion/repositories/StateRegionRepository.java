package com.mindconnect.domain.stateregion.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.stateregion.model.aggregates.StateRegion;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar state_regions.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface StateRegionRepository {

    StateRegion save(StateRegion stateRegion);

    Optional<StateRegion> findById(UUID id);

    List<StateRegion> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
