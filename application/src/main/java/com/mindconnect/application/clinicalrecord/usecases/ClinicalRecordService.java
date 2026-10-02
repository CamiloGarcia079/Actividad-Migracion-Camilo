package com.mindconnect.application.clinicalrecord.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.clinicalrecord.dtos.ClinicalRecordCommand;
import com.mindconnect.application.clinicalrecord.exceptions.ClinicalRecordNotFoundException;
import com.mindconnect.application.clinicalrecord.ports.in.ClinicalRecordUseCase;
import com.mindconnect.domain.clinicalrecord.model.aggregates.ClinicalRecord;
import com.mindconnect.domain.clinicalrecord.repositories.ClinicalRecordRepository;

/**
 * Implementación de los casos de uso de clinical_records.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ClinicalRecordService implements ClinicalRecordUseCase {

    private final ClinicalRecordRepository repository;

    public ClinicalRecordService(ClinicalRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClinicalRecord create(ClinicalRecordCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ClinicalRecord clinicalRecord = new ClinicalRecord(
                UUID.randomUUID(),
                command.patientId(),
                command.creationDate(),
                command.recordNumber(),
                command.openedAt(),
                command.closedAt(),
                command.statusId(),
                now,
                command.createdBy());
        return repository.save(clinicalRecord);
    }

    @Override
    public ClinicalRecord getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordNotFoundException(id));
    }

    @Override
    public List<ClinicalRecord> getAll() {
        return repository.findAll();
    }

    @Override
    public ClinicalRecord update(UUID id, ClinicalRecordCommand command) {
        ClinicalRecord existing = getById(id);
        ClinicalRecord updated = new ClinicalRecord(
                existing.id(),
                command.patientId(),
                command.creationDate(),
                command.recordNumber(),
                command.openedAt(),
                command.closedAt(),
                command.statusId(),
                existing.createdAt(),
                command.createdBy());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ClinicalRecordNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
