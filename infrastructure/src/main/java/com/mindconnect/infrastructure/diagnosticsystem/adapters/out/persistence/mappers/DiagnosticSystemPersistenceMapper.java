package com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.diagnosticsystem.model.aggregates.DiagnosticSystem;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de diagnostic_systems.
 */
@Component
public class DiagnosticSystemPersistenceMapper {

    public DiagnosticSystemJpaEntity toEntity(DiagnosticSystem diagnosticSystem) {
        DiagnosticSystemJpaEntity entity = new DiagnosticSystemJpaEntity();
        entity.setId(diagnosticSystem.id());
        entity.setCode(diagnosticSystem.code());
        entity.setName(diagnosticSystem.name());
        entity.setActive(diagnosticSystem.active());
        entity.setVersion(diagnosticSystem.version());
        entity.setCreatedAt(diagnosticSystem.createdAt());
        entity.setUpdatedAt(diagnosticSystem.updatedAt());
        return entity;
    }

    public DiagnosticSystem toDomain(DiagnosticSystemJpaEntity entity) {
        return new DiagnosticSystem(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getVersion(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
