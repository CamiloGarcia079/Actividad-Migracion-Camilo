package com.mindconnect.application.providermodelai.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.providermodelai.dtos.ProviderModelAiCommand;
import com.mindconnect.domain.providermodelai.model.aggregates.ProviderModelAi;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre provider_models_ai.
 */
public interface ProviderModelAiUseCase {

    ProviderModelAi create(ProviderModelAiCommand command);

    ProviderModelAi getById(UUID id);

    List<ProviderModelAi> getAll();

    ProviderModelAi update(UUID id, ProviderModelAiCommand command);

    void delete(UUID id);
}
