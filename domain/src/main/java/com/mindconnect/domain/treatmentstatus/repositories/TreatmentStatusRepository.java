package com.mindconnect.domain.treatmentstatus.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.treatmentstatus.model.aggregates.TreatmentStatus;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar treatment_statuses.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface TreatmentStatusRepository {

    TreatmentStatus save(TreatmentStatus treatmentStatus);

    Optional<TreatmentStatus> findById(UUID id);

    List<TreatmentStatus> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
