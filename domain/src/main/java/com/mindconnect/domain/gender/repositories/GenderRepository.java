package com.mindconnect.domain.gender.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.gender.model.aggregates.Gender;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar genders.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface GenderRepository {

    Gender save(Gender gender);

    Optional<Gender> findById(UUID id);

    List<Gender> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
