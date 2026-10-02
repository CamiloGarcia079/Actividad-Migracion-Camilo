package com.mindconnect.application.treatmentgoalstatus.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.treatmentgoalstatus.dtos.TreatmentGoalStatusCommand;
import com.mindconnect.domain.treatmentgoalstatus.model.aggregates.TreatmentGoalStatus;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre treatment_goal_statuses.
 */
public interface TreatmentGoalStatusUseCase {

    TreatmentGoalStatus create(TreatmentGoalStatusCommand command);

    TreatmentGoalStatus getById(UUID id);

    List<TreatmentGoalStatus> getAll();

    TreatmentGoalStatus update(UUID id, TreatmentGoalStatusCommand command);

    void delete(UUID id);
}
