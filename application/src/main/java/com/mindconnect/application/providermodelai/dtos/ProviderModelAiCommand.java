package com.mindconnect.application.providermodelai.dtos;

/**
 * Datos que entran al caso de uso de provider_models_ai.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ProviderModelAiCommand(
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        Boolean isActive) {
}
