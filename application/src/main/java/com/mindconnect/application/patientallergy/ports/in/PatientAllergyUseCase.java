package com.mindconnect.application.patientallergy.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.patientallergy.dtos.PatientAllergyCommand;
import com.mindconnect.domain.patientallergy.model.aggregates.PatientAllergy;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre patient_allergies.
 */
public interface PatientAllergyUseCase {

    PatientAllergy create(PatientAllergyCommand command);

    PatientAllergy getById(UUID id);

    List<PatientAllergy> getAll();

    PatientAllergy update(UUID id, PatientAllergyCommand command);

    void delete(UUID id);
}
