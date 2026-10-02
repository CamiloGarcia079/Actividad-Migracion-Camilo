package com.mindconnect.application.patientallergy.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.patientallergy.dtos.PatientAllergyCommand;
import com.mindconnect.application.patientallergy.exceptions.PatientAllergyNotFoundException;
import com.mindconnect.application.patientallergy.ports.in.PatientAllergyUseCase;
import com.mindconnect.domain.patientallergy.model.aggregates.PatientAllergy;
import com.mindconnect.domain.patientallergy.repositories.PatientAllergyRepository;

/**
 * Implementación de los casos de uso de patient_allergies.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class PatientAllergyService implements PatientAllergyUseCase {

    private final PatientAllergyRepository repository;

    public PatientAllergyService(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientAllergy create(PatientAllergyCommand command) {
        LocalDateTime now = LocalDateTime.now();
        PatientAllergy patientAllergy = new PatientAllergy(
                UUID.randomUUID(),
                command.patientId(),
                command.substance(),
                command.reaction(),
                command.severity(),
                command.active(),
                command.recordedAt(),
                command.recordedBy(),
                now,
                now);
        return repository.save(patientAllergy);
    }

    @Override
    public PatientAllergy getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PatientAllergyNotFoundException(id));
    }

    @Override
    public List<PatientAllergy> getAll() {
        return repository.findAll();
    }

    @Override
    public PatientAllergy update(UUID id, PatientAllergyCommand command) {
        PatientAllergy existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        PatientAllergy updated = new PatientAllergy(
                existing.id(),
                command.patientId(),
                command.substance(),
                command.reaction(),
                command.severity(),
                command.active(),
                command.recordedAt(),
                command.recordedBy(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new PatientAllergyNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
