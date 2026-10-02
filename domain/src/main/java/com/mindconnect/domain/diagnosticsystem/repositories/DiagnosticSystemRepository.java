package com.mindconnect.domain.diagnosticsystem.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.diagnosticsystem.model.aggregates.DiagnosticSystem;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar diagnostic_systems.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface DiagnosticSystemRepository {

    DiagnosticSystem save(DiagnosticSystem diagnosticSystem);

    Optional<DiagnosticSystem> findById(UUID id);

    List<DiagnosticSystem> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
