package com.mindconnect.domain.clinicalnote.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.clinicalnote.model.aggregates.ClinicalNote;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar clinical_notes.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ClinicalNoteRepository {

    ClinicalNote save(ClinicalNote clinicalNote);

    Optional<ClinicalNote> findById(UUID id);

    List<ClinicalNote> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
