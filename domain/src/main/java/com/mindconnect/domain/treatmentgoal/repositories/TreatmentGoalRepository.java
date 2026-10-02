package com.mindconnect.domain.treatmentgoal.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.treatmentgoal.model.aggregates.TreatmentGoal;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar treatment_goals.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface TreatmentGoalRepository {

    TreatmentGoal save(TreatmentGoal treatmentGoal);

    Optional<TreatmentGoal> findById(UUID id);

    List<TreatmentGoal> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
