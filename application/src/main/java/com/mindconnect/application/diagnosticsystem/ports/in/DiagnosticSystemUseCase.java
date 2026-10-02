package com.mindconnect.application.diagnosticsystem.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.diagnosticsystem.dtos.DiagnosticSystemCommand;
import com.mindconnect.domain.diagnosticsystem.model.aggregates.DiagnosticSystem;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre diagnostic_systems.
 */
public interface DiagnosticSystemUseCase {

    DiagnosticSystem create(DiagnosticSystemCommand command);

    DiagnosticSystem getById(UUID id);

    List<DiagnosticSystem> getAll();

    DiagnosticSystem update(UUID id, DiagnosticSystemCommand command);

    void delete(UUID id);
}
