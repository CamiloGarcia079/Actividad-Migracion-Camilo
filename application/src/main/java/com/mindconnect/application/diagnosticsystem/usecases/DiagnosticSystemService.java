package com.mindconnect.application.diagnosticsystem.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.diagnosticsystem.dtos.DiagnosticSystemCommand;
import com.mindconnect.application.diagnosticsystem.exceptions.DiagnosticSystemNotFoundException;
import com.mindconnect.application.diagnosticsystem.ports.in.DiagnosticSystemUseCase;
import com.mindconnect.domain.diagnosticsystem.model.aggregates.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.repositories.DiagnosticSystemRepository;

/**
 * Implementación de los casos de uso de diagnostic_systems.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class DiagnosticSystemService implements DiagnosticSystemUseCase {

    private final DiagnosticSystemRepository repository;

    public DiagnosticSystemService(DiagnosticSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiagnosticSystem create(DiagnosticSystemCommand command) {
        LocalDateTime now = LocalDateTime.now();
        DiagnosticSystem diagnosticSystem = new DiagnosticSystem(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                command.version(),
                now,
                now);
        return repository.save(diagnosticSystem);
    }

    @Override
    public DiagnosticSystem getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DiagnosticSystemNotFoundException(id));
    }

    @Override
    public List<DiagnosticSystem> getAll() {
        return repository.findAll();
    }

    @Override
    public DiagnosticSystem update(UUID id, DiagnosticSystemCommand command) {
        DiagnosticSystem existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        DiagnosticSystem updated = new DiagnosticSystem(
                existing.id(),
                command.code(),
                command.name(),
                command.active(),
                command.version(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new DiagnosticSystemNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
