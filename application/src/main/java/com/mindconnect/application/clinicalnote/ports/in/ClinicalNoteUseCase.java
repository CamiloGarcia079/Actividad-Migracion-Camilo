package com.mindconnect.application.clinicalnote.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.clinicalnote.dtos.ClinicalNoteCommand;
import com.mindconnect.domain.clinicalnote.model.aggregates.ClinicalNote;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre clinical_notes.
 */
public interface ClinicalNoteUseCase {

    ClinicalNote create(ClinicalNoteCommand command);

    ClinicalNote getById(UUID id);

    List<ClinicalNote> getAll();

    ClinicalNote update(UUID id, ClinicalNoteCommand command);

    void delete(UUID id);
}
