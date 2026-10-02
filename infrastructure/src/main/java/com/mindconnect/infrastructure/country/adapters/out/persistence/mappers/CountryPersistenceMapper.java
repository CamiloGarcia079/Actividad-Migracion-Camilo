package com.mindconnect.infrastructure.country.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.country.model.aggregates.Country;
import com.mindconnect.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de countries.
 */
@Component
public class CountryPersistenceMapper {

    public CountryJpaEntity toEntity(Country country) {
        CountryJpaEntity entity = new CountryJpaEntity();
        entity.setId(country.id());
        entity.setNameCountry(country.nameCountry());
        entity.setCodeCountry(country.codeCountry());
        entity.setDescription(country.description());
        entity.setIsActive(country.isActive());
        entity.setTelephonePrefix(country.telephonePrefix());
        entity.setCreatedAt(country.createdAt());
        entity.setUpdatedAt(country.updatedAt());
        return entity;
    }

    public Country toDomain(CountryJpaEntity entity) {
        return new Country(
                entity.getId(),
                entity.getNameCountry(),
                entity.getCodeCountry(),
                entity.getDescription(),
                entity.getIsActive(),
                entity.getTelephonePrefix(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
