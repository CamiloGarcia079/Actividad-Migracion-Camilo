package com.mindconnect.domain.aimodel.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.aimodel.model.aggregates.AiModel;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar ai_models.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface AiModelRepository {

    AiModel save(AiModel aiModel);

    Optional<AiModel> findById(UUID id);

    List<AiModel> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
