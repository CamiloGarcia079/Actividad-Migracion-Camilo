package com.mindconnect.domain.consenttype.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.consenttype.model.aggregates.ConsentType;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar consent_types.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ConsentTypeRepository {

    ConsentType save(ConsentType consentType);

    Optional<ConsentType> findById(UUID id);

    List<ConsentType> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
