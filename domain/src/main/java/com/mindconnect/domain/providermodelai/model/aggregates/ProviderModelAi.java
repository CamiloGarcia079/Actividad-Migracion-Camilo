package com.mindconnect.domain.providermodelai.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla provider_models_ai.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ProviderModelAi(
        UUID id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ProviderModelAi {
        DomainValidations.required(id, "id");
        DomainValidations.required(nameProviderAi, "nameProviderAi");
        DomainValidations.required(razonSocial, "razonSocial");
        DomainValidations.required(sitioWeb, "sitioWeb");
        DomainValidations.required(isActive, "isActive");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
