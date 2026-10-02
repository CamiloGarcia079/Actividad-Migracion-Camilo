package com.mindconnect.application.aimodel.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.aimodel.dtos.AiModelCommand;
import com.mindconnect.domain.aimodel.model.aggregates.AiModel;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre ai_models.
 */
public interface AiModelUseCase {

    AiModel create(AiModelCommand command);

    AiModel getById(UUID id);

    List<AiModel> getAll();

    AiModel update(UUID id, AiModelCommand command);

    void delete(UUID id);
}
