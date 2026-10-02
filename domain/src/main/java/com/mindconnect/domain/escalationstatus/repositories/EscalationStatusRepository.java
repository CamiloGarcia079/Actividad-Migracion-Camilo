package com.mindconnect.domain.escalationstatus.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.escalationstatus.model.aggregates.EscalationStatus;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar escalations_statuses.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface EscalationStatusRepository {

    EscalationStatus save(EscalationStatus escalationStatus);

    Optional<EscalationStatus> findById(UUID id);

    List<EscalationStatus> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
