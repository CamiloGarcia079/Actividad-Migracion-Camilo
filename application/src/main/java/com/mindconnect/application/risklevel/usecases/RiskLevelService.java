package com.mindconnect.application.risklevel.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.risklevel.dtos.RiskLevelCommand;
import com.mindconnect.application.risklevel.exceptions.RiskLevelNotFoundException;
import com.mindconnect.application.risklevel.ports.in.RiskLevelUseCase;
import com.mindconnect.domain.risklevel.model.aggregates.RiskLevel;
import com.mindconnect.domain.risklevel.repositories.RiskLevelRepository;

/**
 * Implementación de los casos de uso de risk_levels.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class RiskLevelService implements RiskLevelUseCase {

    private final RiskLevelRepository repository;

    public RiskLevelService(RiskLevelRepository repository) {
        this.repository = repository;
    }

    @Override
    public RiskLevel create(RiskLevelCommand command) {
        LocalDateTime now = LocalDateTime.now();
        RiskLevel riskLevel = new RiskLevel(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                command.severity(),
                now,
                now);
        return repository.save(riskLevel);
    }

    @Override
    public RiskLevel getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RiskLevelNotFoundException(id));
    }

    @Override
    public List<RiskLevel> getAll() {
        return repository.findAll();
    }

    @Override
    public RiskLevel update(UUID id, RiskLevelCommand command) {
        RiskLevel existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        RiskLevel updated = new RiskLevel(
                existing.id(),
                command.code(),
                command.name(),
                command.active(),
                command.severity(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RiskLevelNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
