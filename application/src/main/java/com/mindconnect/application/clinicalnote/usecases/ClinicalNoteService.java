package com.mindconnect.application.clinicalnote.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.clinicalnote.dtos.ClinicalNoteCommand;
import com.mindconnect.application.clinicalnote.exceptions.ClinicalNoteNotFoundException;
import com.mindconnect.application.clinicalnote.ports.in.ClinicalNoteUseCase;
import com.mindconnect.domain.clinicalnote.model.aggregates.ClinicalNote;
import com.mindconnect.domain.clinicalnote.repositories.ClinicalNoteRepository;

/**
 * Implementación de los casos de uso de clinical_notes.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ClinicalNoteService implements ClinicalNoteUseCase {

    private final ClinicalNoteRepository repository;

    public ClinicalNoteService(ClinicalNoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClinicalNote create(ClinicalNoteCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ClinicalNote clinicalNote = new ClinicalNote(
                UUID.randomUUID(),
                command.encounterId(),
                command.professionalId(),
                command.subjective(),
                command.objective(),
                command.assessment(),
                command.plan(),
                command.additionalNotes(),
                command.signedAt(),
                now,
                now);
        return repository.save(clinicalNote);
    }

    @Override
    public ClinicalNote getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClinicalNoteNotFoundException(id));
    }

    @Override
    public List<ClinicalNote> getAll() {
        return repository.findAll();
    }

    @Override
    public ClinicalNote update(UUID id, ClinicalNoteCommand command) {
        ClinicalNote existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ClinicalNote updated = new ClinicalNote(
                existing.id(),
                command.encounterId(),
                command.professionalId(),
                command.subjective(),
                command.objective(),
                command.assessment(),
                command.plan(),
                command.additionalNotes(),
                command.signedAt(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ClinicalNoteNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
