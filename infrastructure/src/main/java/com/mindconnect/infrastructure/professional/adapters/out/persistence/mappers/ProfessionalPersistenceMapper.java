package com.mindconnect.infrastructure.professional.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.professional.model.aggregates.Professional;
import com.mindconnect.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de professionals.
 */
@Component
public class ProfessionalPersistenceMapper {

    public ProfessionalJpaEntity toEntity(Professional professional) {
        ProfessionalJpaEntity entity = new ProfessionalJpaEntity();
        entity.setId(professional.id());
        entity.setDocumentTypeId(professional.documentTypeId());
        entity.setDocumentNumber(professional.documentNumber());
        entity.setFirstName(professional.firstName());
        entity.setLastName(professional.lastName());
        entity.setProfessionalType(professional.professionalType());
        entity.setLicenseNumber(professional.licenseNumber());
        entity.setActive(professional.active());
        entity.setCityId(professional.cityId());
        entity.setCreatedAt(professional.createdAt());
        entity.setUpdatedAt(professional.updatedAt());
        return entity;
    }

    public Professional toDomain(ProfessionalJpaEntity entity) {
        return new Professional(
                entity.getId(),
                entity.getDocumentTypeId(),
                entity.getDocumentNumber(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getProfessionalType(),
                entity.getLicenseNumber(),
                entity.getActive(),
                entity.getCityId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
