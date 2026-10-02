package com.mindconnect.application.clinicalrecordstatus.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de clinical_record_statuses con el id pedido.
 */
public class ClinicalRecordStatusNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ClinicalRecordStatusNotFoundException(UUID id) {
        super("No se encontró un registro de clinical_record_statuses con id " + id);
    }
}
