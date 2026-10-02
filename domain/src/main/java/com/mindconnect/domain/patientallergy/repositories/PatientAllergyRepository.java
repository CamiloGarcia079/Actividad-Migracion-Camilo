package com.mindconnect.domain.patientallergy.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.patientallergy.model.aggregates.PatientAllergy;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar patient_allergies.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface PatientAllergyRepository {

    PatientAllergy save(PatientAllergy patientAllergy);

    Optional<PatientAllergy> findById(UUID id);

    List<PatientAllergy> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
