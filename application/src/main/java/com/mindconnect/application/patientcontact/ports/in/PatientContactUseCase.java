package com.mindconnect.application.patientcontact.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.patientcontact.dtos.PatientContactCommand;
import com.mindconnect.domain.patientcontact.model.aggregates.PatientContact;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre patient_contacts.
 */
public interface PatientContactUseCase {

    PatientContact create(PatientContactCommand command);

    PatientContact getById(UUID id);

    List<PatientContact> getAll();

    PatientContact update(UUID id, PatientContactCommand command);

    void delete(UUID id);
}
