package com.mindconnect.domain.airunstatus.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.airunstatus.model.aggregates.AiRunStatus;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar ai_runs_statuses.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface AiRunStatusRepository {

    AiRunStatus save(AiRunStatus aiRunStatus);

    Optional<AiRunStatus> findById(UUID id);

    List<AiRunStatus> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
