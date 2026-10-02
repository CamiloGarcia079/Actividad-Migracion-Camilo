package com.mindconnect.infrastructure.providermodelai.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.providermodelai.model.aggregates.ProviderModelAi;

/**
 * Respuesta JSON de la API para un registro de provider_models_ai.
 */
public record ProviderModelAiResponse(
        UUID id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ProviderModelAiResponse from(ProviderModelAi providerModelAi) {
        return new ProviderModelAiResponse(
                providerModelAi.id(), providerModelAi.nameProviderAi(), providerModelAi.razonSocial(), providerModelAi.sitioWeb(), providerModelAi.isActive(), providerModelAi.createdAt(), providerModelAi.updatedAt());
    }
}
