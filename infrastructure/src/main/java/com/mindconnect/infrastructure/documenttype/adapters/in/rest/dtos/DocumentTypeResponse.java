package com.mindconnect.infrastructure.documenttype.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.documenttype.model.aggregates.DocumentType;

/**
 * Respuesta JSON de la API para un registro de document_types.
 */
public record DocumentTypeResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static DocumentTypeResponse from(DocumentType documentType) {
        return new DocumentTypeResponse(
                documentType.id(), documentType.code(), documentType.name(), documentType.active(), documentType.createdAt(), documentType.updatedAt());
    }
}
