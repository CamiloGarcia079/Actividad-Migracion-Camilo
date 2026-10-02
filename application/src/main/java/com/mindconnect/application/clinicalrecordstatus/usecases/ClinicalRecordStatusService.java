package com.mindconnect.application.clinicalrecordstatus.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.clinicalrecordstatus.dtos.ClinicalRecordStatusCommand;
import com.mindconnect.application.clinicalrecordstatus.exceptions.ClinicalRecordStatusNotFoundException;
import com.mindconnect.application.clinicalrecordstatus.ports.in.ClinicalRecordStatusUseCase;
import com.mindconnect.domain.clinicalrecordstatus.model.aggregates.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.repositories.ClinicalRecordStatusRepository;

/**
 * Implementación de los casos de uso de clinical_record_statuses.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ClinicalRecordStatusService implements ClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public ClinicalRecordStatusService(ClinicalRecordStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClinicalRecordStatus create(ClinicalRecordStatusCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ClinicalRecordStatus clinicalRecordStatus = new ClinicalRecordStatus(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                now,
                now);
        return repository.save(clinicalRecordStatus);
    }

    @Override
    public ClinicalRecordStatus getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundException(id));
    }

    @Override
    public List<ClinicalRecordStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public ClinicalRecordStatus update(UUID id, ClinicalRecordStatusCommand command) {
        ClinicalRecordStatus existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ClinicalRecordStatus updated = new ClinicalRecordStatus(
                existing.id(),
                command.code(),
                command.name(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ClinicalRecordStatusNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
