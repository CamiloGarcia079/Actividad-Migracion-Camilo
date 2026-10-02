package com.mindconnect.domain.treatmentgoalstatus.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.treatmentgoalstatus.model.aggregates.TreatmentGoalStatus;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar treatment_goal_statuses.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface TreatmentGoalStatusRepository {

    TreatmentGoalStatus save(TreatmentGoalStatus treatmentGoalStatus);

    Optional<TreatmentGoalStatus> findById(UUID id);

    List<TreatmentGoalStatus> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
