package com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.medicationroute.model.aggregates.MedicationRoute;
import com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de medication_routes.
 */
@Component
public class MedicationRoutePersistenceMapper {

    public MedicationRouteJpaEntity toEntity(MedicationRoute medicationRoute) {
        MedicationRouteJpaEntity entity = new MedicationRouteJpaEntity();
        entity.setId(medicationRoute.id());
        entity.setCode(medicationRoute.code());
        entity.setName(medicationRoute.name());
        entity.setActive(medicationRoute.active());
        entity.setCreatedAt(medicationRoute.createdAt());
        entity.setUpdatedAt(medicationRoute.updatedAt());
        return entity;
    }

    public MedicationRoute toDomain(MedicationRouteJpaEntity entity) {
        return new MedicationRoute(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
