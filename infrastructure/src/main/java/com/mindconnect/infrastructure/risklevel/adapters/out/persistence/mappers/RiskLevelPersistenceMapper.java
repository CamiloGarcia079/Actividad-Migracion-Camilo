package com.mindconnect.infrastructure.risklevel.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.risklevel.model.aggregates.RiskLevel;
import com.mindconnect.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de risk_levels.
 */
@Component
public class RiskLevelPersistenceMapper {

    public RiskLevelJpaEntity toEntity(RiskLevel riskLevel) {
        RiskLevelJpaEntity entity = new RiskLevelJpaEntity();
        entity.setId(riskLevel.id());
        entity.setCode(riskLevel.code());
        entity.setName(riskLevel.name());
        entity.setActive(riskLevel.active());
        entity.setSeverity(riskLevel.severity());
        entity.setCreatedAt(riskLevel.createdAt());
        entity.setUpdatedAt(riskLevel.updatedAt());
        return entity;
    }

    public RiskLevel toDomain(RiskLevelJpaEntity entity) {
        return new RiskLevel(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getSeverity(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
