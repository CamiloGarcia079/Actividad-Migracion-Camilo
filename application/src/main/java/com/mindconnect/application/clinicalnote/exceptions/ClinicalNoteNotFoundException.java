package com.mindconnect.application.clinicalnote.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de clinical_notes con el id pedido.
 */
public class ClinicalNoteNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ClinicalNoteNotFoundException(UUID id) {
        super("No se encontró un registro de clinical_notes con id " + id);
    }
}
