package com.mindconnect.application.documenttype.dtos;

/**
 * Datos que entran al caso de uso de document_types.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record DocumentTypeCommand(
        String code,
        String name,
        Boolean active) {
}
