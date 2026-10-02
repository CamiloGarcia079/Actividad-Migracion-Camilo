package com.mindconnect.infrastructure.stateregion.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.stateregion.model.aggregates.StateRegion;
import com.mindconnect.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de state_regions.
 */
@Component
public class StateRegionPersistenceMapper {

    public StateRegionJpaEntity toEntity(StateRegion stateRegion) {
        StateRegionJpaEntity entity = new StateRegionJpaEntity();
        entity.setId(stateRegion.id());
        entity.setNameRegion(stateRegion.nameRegion());
        entity.setCodeRegion(stateRegion.codeRegion());
        entity.setDescription(stateRegion.description());
        entity.setIsActive(stateRegion.isActive());
        entity.setCountryId(stateRegion.countryId());
        entity.setCreatedAt(stateRegion.createdAt());
        entity.setUpdatedAt(stateRegion.updatedAt());
        return entity;
    }

    public StateRegion toDomain(StateRegionJpaEntity entity) {
        return new StateRegion(
                entity.getId(),
                entity.getNameRegion(),
                entity.getCodeRegion(),
                entity.getDescription(),
                entity.getIsActive(),
                entity.getCountryId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
