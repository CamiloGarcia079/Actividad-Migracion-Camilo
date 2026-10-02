package com.mindconnect.application.treatmentgoal.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de treatment_goals con el id pedido.
 */
public class TreatmentGoalNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public TreatmentGoalNotFoundException(UUID id) {
        super("No se encontró un registro de treatment_goals con id " + id);
    }
}
