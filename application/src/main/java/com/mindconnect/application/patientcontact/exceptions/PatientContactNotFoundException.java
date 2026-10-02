package com.mindconnect.application.patientcontact.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de patient_contacts con el id pedido.
 */
public class PatientContactNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PatientContactNotFoundException(UUID id) {
        super("No se encontró un registro de patient_contacts con id " + id);
    }
}
