package com.mindconnect.application.treatmentgoal.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.treatmentgoal.dtos.TreatmentGoalCommand;
import com.mindconnect.domain.treatmentgoal.model.aggregates.TreatmentGoal;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre treatment_goals.
 */
public interface TreatmentGoalUseCase {

    TreatmentGoal create(TreatmentGoalCommand command);

    TreatmentGoal getById(UUID id);

    List<TreatmentGoal> getAll();

    TreatmentGoal update(UUID id, TreatmentGoalCommand command);

    void delete(UUID id);
}
