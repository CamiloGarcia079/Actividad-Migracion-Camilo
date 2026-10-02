package com.mindconnect.domain.patientcontact.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.patientcontact.model.aggregates.PatientContact;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar patient_contacts.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface PatientContactRepository {

    PatientContact save(PatientContact patientContact);

    Optional<PatientContact> findById(UUID id);

    List<PatientContact> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
