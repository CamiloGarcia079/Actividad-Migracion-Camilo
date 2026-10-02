package com.mindconnect.application.treatmentgoalstatus.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de treatment_goal_statuses con el id pedido.
 */
public class TreatmentGoalStatusNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public TreatmentGoalStatusNotFoundException(UUID id) {
        super("No se encontró un registro de treatment_goal_statuses con id " + id);
    }
}
