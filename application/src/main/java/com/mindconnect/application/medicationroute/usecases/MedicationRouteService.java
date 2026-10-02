package com.mindconnect.application.medicationroute.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.medicationroute.dtos.MedicationRouteCommand;
import com.mindconnect.application.medicationroute.exceptions.MedicationRouteNotFoundException;
import com.mindconnect.application.medicationroute.ports.in.MedicationRouteUseCase;
import com.mindconnect.domain.medicationroute.model.aggregates.MedicationRoute;
import com.mindconnect.domain.medicationroute.repositories.MedicationRouteRepository;

/**
 * Implementación de los casos de uso de medication_routes.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class MedicationRouteService implements MedicationRouteUseCase {

    private final MedicationRouteRepository repository;

    public MedicationRouteService(MedicationRouteRepository repository) {
        this.repository = repository;
    }

    @Override
    public MedicationRoute create(MedicationRouteCommand command) {
        LocalDateTime now = LocalDateTime.now();
        MedicationRoute medicationRoute = new MedicationRoute(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                now,
                now);
        return repository.save(medicationRoute);
    }

    @Override
    public MedicationRoute getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new MedicationRouteNotFoundException(id));
    }

    @Override
    public List<MedicationRoute> getAll() {
        return repository.findAll();
    }

    @Override
    public MedicationRoute update(UUID id, MedicationRouteCommand command) {
        MedicationRoute existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        MedicationRoute updated = new MedicationRoute(
                existing.id(),
                command.code(),
                command.name(),
                command.active(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new MedicationRouteNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
