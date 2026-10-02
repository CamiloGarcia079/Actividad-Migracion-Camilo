package com.mindconnect.application.patient.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.patient.dtos.PatientCommand;
import com.mindconnect.application.patient.exceptions.PatientNotFoundException;
import com.mindconnect.application.patient.ports.in.PatientUseCase;
import com.mindconnect.domain.patient.model.aggregates.Patient;
import com.mindconnect.domain.patient.repositories.PatientRepository;

/**
 * Implementación de los casos de uso de patients.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class PatientService implements PatientUseCase {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Patient create(PatientCommand command) {
        LocalDateTime now = LocalDateTime.now();
        Patient patient = new Patient(
                UUID.randomUUID(),
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.middleName(),
                command.lastName(),
                command.secondLastName(),
                command.birthDate(),
                command.biologicalSexId(),
                command.genderIdentity(),
                command.email(),
                command.phone(),
                command.address(),
                command.active(),
                now,
                command.createdBy(),
                now,
                command.updatedBy(),
                command.cityId());
        return repository.save(patient);
    }

    @Override
    public Patient getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public List<Patient> getAll() {
        return repository.findAll();
    }

    @Override
    public Patient update(UUID id, PatientCommand command) {
        Patient existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        Patient updated = new Patient(
                existing.id(),
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.middleName(),
                command.lastName(),
                command.secondLastName(),
                command.birthDate(),
                command.biologicalSexId(),
                command.genderIdentity(),
                command.email(),
                command.phone(),
                command.address(),
                command.active(),
                existing.createdAt(),
                command.createdBy(),
                now,
                command.updatedBy(),
                command.cityId());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new PatientNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
