package com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.citymunicipality.model.aggregates.CityMunicipality;
import com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de city_municipalities.
 */
@Component
public class CityMunicipalityPersistenceMapper {

    public CityMunicipalityJpaEntity toEntity(CityMunicipality cityMunicipality) {
        CityMunicipalityJpaEntity entity = new CityMunicipalityJpaEntity();
        entity.setId(cityMunicipality.id());
        entity.setNameCity(cityMunicipality.nameCity());
        entity.setCodeCity(cityMunicipality.codeCity());
        entity.setDescription(cityMunicipality.description());
        entity.setIsActive(cityMunicipality.isActive());
        entity.setRegionId(cityMunicipality.regionId());
        entity.setCreatedAt(cityMunicipality.createdAt());
        entity.setUpdatedAt(cityMunicipality.updatedAt());
        return entity;
    }

    public CityMunicipality toDomain(CityMunicipalityJpaEntity entity) {
        return new CityMunicipality(
                entity.getId(),
                entity.getNameCity(),
                entity.getCodeCity(),
                entity.getDescription(),
                entity.getIsActive(),
                entity.getRegionId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
