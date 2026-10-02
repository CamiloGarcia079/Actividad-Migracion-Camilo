package com.mindconnect.domain.providermodelai.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.providermodelai.model.aggregates.ProviderModelAi;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar provider_models_ai.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ProviderModelAiRepository {

    ProviderModelAi save(ProviderModelAi providerModelAi);

    Optional<ProviderModelAi> findById(UUID id);

    List<ProviderModelAi> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
