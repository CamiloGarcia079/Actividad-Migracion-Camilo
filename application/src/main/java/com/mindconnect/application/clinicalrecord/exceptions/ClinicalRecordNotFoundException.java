package com.mindconnect.application.clinicalrecord.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de clinical_records con el id pedido.
 */
public class ClinicalRecordNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ClinicalRecordNotFoundException(UUID id) {
        super("No se encontró un registro de clinical_records con id " + id);
    }
}
