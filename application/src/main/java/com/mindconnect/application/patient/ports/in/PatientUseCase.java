package com.mindconnect.application.patient.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.patient.dtos.PatientCommand;
import com.mindconnect.domain.patient.model.aggregates.Patient;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre patients.
 */
public interface PatientUseCase {

    Patient create(PatientCommand command);

    Patient getById(UUID id);

    List<Patient> getAll();

    Patient update(UUID id, PatientCommand command);

    void delete(UUID id);
}
