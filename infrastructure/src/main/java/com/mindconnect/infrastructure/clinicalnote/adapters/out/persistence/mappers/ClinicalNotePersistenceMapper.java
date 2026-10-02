package com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.clinicalnote.model.aggregates.ClinicalNote;
import com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de clinical_notes.
 */
@Component
public class ClinicalNotePersistenceMapper {

    public ClinicalNoteJpaEntity toEntity(ClinicalNote clinicalNote) {
        ClinicalNoteJpaEntity entity = new ClinicalNoteJpaEntity();
        entity.setId(clinicalNote.id());
        entity.setEncounterId(clinicalNote.encounterId());
        entity.setProfessionalId(clinicalNote.professionalId());
        entity.setSubjective(clinicalNote.subjective());
        entity.setObjective(clinicalNote.objective());
        entity.setAssessment(clinicalNote.assessment());
        entity.setPlan(clinicalNote.plan());
        entity.setAdditionalNotes(clinicalNote.additionalNotes());
        entity.setSignedAt(clinicalNote.signedAt());
        entity.setCreatedAt(clinicalNote.createdAt());
        entity.setUpdatedAt(clinicalNote.updatedAt());
        return entity;
    }

    public ClinicalNote toDomain(ClinicalNoteJpaEntity entity) {
        return new ClinicalNote(
                entity.getId(),
                entity.getEncounterId(),
                entity.getProfessionalId(),
                entity.getSubjective(),
                entity.getObjective(),
                entity.getAssessment(),
                entity.getPlan(),
                entity.getAdditionalNotes(),
                entity.getSignedAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
