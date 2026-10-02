package com.mindconnect.application.patientallergy.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de patient_allergies con el id pedido.
 */
public class PatientAllergyNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PatientAllergyNotFoundException(UUID id) {
        super("No se encontró un registro de patient_allergies con id " + id);
    }
}
