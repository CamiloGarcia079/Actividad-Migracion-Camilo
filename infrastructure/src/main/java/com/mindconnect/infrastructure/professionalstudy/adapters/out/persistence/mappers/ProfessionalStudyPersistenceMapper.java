package com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.professionalstudy.model.aggregates.ProfessionalStudy;
import com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de professional_studies.
 */
@Component
public class ProfessionalStudyPersistenceMapper {

    public ProfessionalStudyJpaEntity toEntity(ProfessionalStudy professionalStudy) {
        ProfessionalStudyJpaEntity entity = new ProfessionalStudyJpaEntity();
        entity.setId(professionalStudy.id());
        entity.setStudyId(professionalStudy.studyId());
        entity.setProfessionalId(professionalStudy.professionalId());
        entity.setTitle(professionalStudy.title());
        entity.setUniversity(professionalStudy.university());
        entity.setIsValid(professionalStudy.isValid());
        entity.setResolutionNumber(professionalStudy.resolutionNumber());
        entity.setCountryId(professionalStudy.countryId());
        entity.setCreatedAt(professionalStudy.createdAt());
        entity.setUpdatedAt(professionalStudy.updatedAt());
        return entity;
    }

    public ProfessionalStudy toDomain(ProfessionalStudyJpaEntity entity) {
        return new ProfessionalStudy(
                entity.getId(),
                entity.getStudyId(),
                entity.getProfessionalId(),
                entity.getTitle(),
                entity.getUniversity(),
                entity.getIsValid(),
                entity.getResolutionNumber(),
                entity.getCountryId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
