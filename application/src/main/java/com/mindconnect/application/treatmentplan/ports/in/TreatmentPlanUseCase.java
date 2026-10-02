package com.mindconnect.application.treatmentplan.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.treatmentplan.dtos.TreatmentPlanCommand;
import com.mindconnect.domain.treatmentplan.model.aggregates.TreatmentPlan;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre treatment_plans.
 */
public interface TreatmentPlanUseCase {

    TreatmentPlan create(TreatmentPlanCommand command);

    TreatmentPlan getById(UUID id);

    List<TreatmentPlan> getAll();

    TreatmentPlan update(UUID id, TreatmentPlanCommand command);

    void delete(UUID id);
}
