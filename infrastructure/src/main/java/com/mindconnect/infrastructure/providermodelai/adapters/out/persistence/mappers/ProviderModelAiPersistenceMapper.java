package com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.providermodelai.model.aggregates.ProviderModelAi;
import com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.entity.ProviderModelAiJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de provider_models_ai.
 */
@Component
public class ProviderModelAiPersistenceMapper {

    public ProviderModelAiJpaEntity toEntity(ProviderModelAi providerModelAi) {
        ProviderModelAiJpaEntity entity = new ProviderModelAiJpaEntity();
        entity.setId(providerModelAi.id());
        entity.setNameProviderAi(providerModelAi.nameProviderAi());
        entity.setRazonSocial(providerModelAi.razonSocial());
        entity.setSitioWeb(providerModelAi.sitioWeb());
        entity.setIsActive(providerModelAi.isActive());
        entity.setCreatedAt(providerModelAi.createdAt());
        entity.setUpdatedAt(providerModelAi.updatedAt());
        return entity;
    }

    public ProviderModelAi toDomain(ProviderModelAiJpaEntity entity) {
        return new ProviderModelAi(
                entity.getId(),
                entity.getNameProviderAi(),
                entity.getRazonSocial(),
                entity.getSitioWeb(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
