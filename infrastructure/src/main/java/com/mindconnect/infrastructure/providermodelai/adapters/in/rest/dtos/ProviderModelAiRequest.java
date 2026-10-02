package com.mindconnect.infrastructure.providermodelai.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.providermodelai.dtos.ProviderModelAiCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de provider_models_ai.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ProviderModelAiRequest(
        @NotBlank @Size(max = 100) String nameProviderAi,
        @NotBlank @Size(max = 150) String razonSocial,
        @NotBlank String sitioWeb,
        @NotNull Boolean isActive) {

    public ProviderModelAiCommand toCommand() {
        return new ProviderModelAiCommand(
                nameProviderAi, razonSocial, sitioWeb, isActive);
    }
}
