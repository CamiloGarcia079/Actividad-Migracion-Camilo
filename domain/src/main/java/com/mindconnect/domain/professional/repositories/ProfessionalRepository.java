package com.mindconnect.domain.professional.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.professional.model.aggregates.Professional;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar professionals.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ProfessionalRepository {

    Professional save(Professional professional);

    Optional<Professional> findById(UUID id);

    List<Professional> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
