package com.mindconnect.domain.patient.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.patient.model.aggregates.Patient;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar patients.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface PatientRepository {

    Patient save(Patient patient);

    Optional<Patient> findById(UUID id);

    List<Patient> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
