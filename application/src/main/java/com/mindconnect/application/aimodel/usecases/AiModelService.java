package com.mindconnect.application.aimodel.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.aimodel.dtos.AiModelCommand;
import com.mindconnect.application.aimodel.exceptions.AiModelNotFoundException;
import com.mindconnect.application.aimodel.ports.in.AiModelUseCase;
import com.mindconnect.domain.aimodel.model.aggregates.AiModel;
import com.mindconnect.domain.aimodel.repositories.AiModelRepository;

/**
 * Implementación de los casos de uso de ai_models.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class AiModelService implements AiModelUseCase {

    private final AiModelRepository repository;

    public AiModelService(AiModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public AiModel create(AiModelCommand command) {
        LocalDateTime now = LocalDateTime.now();
        AiModel aiModel = new AiModel(
                UUID.randomUUID(),
                command.providerModelId(),
                command.nameModel(),
                command.modelKey(),
                command.inputTokenPrice(),
                command.outputTokenPrice(),
                command.maxTokens(),
                command.contextWindow(),
                command.isActive(),
                now,
                now);
        return repository.save(aiModel);
    }

    @Override
    public AiModel getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AiModelNotFoundException(id));
    }

    @Override
    public List<AiModel> getAll() {
        return repository.findAll();
    }

    @Override
    public AiModel update(UUID id, AiModelCommand command) {
        AiModel existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        AiModel updated = new AiModel(
                existing.id(),
                command.providerModelId(),
                command.nameModel(),
                command.modelKey(),
                command.inputTokenPrice(),
                command.outputTokenPrice(),
                command.maxTokens(),
                command.contextWindow(),
                command.isActive(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new AiModelNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
