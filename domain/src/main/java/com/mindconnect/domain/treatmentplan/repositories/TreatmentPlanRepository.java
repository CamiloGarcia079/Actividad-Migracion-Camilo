package com.mindconnect.domain.treatmentplan.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.treatmentplan.model.aggregates.TreatmentPlan;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar treatment_plans.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface TreatmentPlanRepository {

    TreatmentPlan save(TreatmentPlan treatmentPlan);

    Optional<TreatmentPlan> findById(UUID id);

    List<TreatmentPlan> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
