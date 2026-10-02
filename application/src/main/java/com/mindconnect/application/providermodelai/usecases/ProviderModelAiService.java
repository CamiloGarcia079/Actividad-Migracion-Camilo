package com.mindconnect.application.providermodelai.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.providermodelai.dtos.ProviderModelAiCommand;
import com.mindconnect.application.providermodelai.exceptions.ProviderModelAiNotFoundException;
import com.mindconnect.application.providermodelai.ports.in.ProviderModelAiUseCase;
import com.mindconnect.domain.providermodelai.model.aggregates.ProviderModelAi;
import com.mindconnect.domain.providermodelai.repositories.ProviderModelAiRepository;

/**
 * Implementación de los casos de uso de provider_models_ai.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ProviderModelAiService implements ProviderModelAiUseCase {

    private final ProviderModelAiRepository repository;

    public ProviderModelAiService(ProviderModelAiRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProviderModelAi create(ProviderModelAiCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ProviderModelAi providerModelAi = new ProviderModelAi(
                UUID.randomUUID(),
                command.nameProviderAi(),
                command.razonSocial(),
                command.sitioWeb(),
                command.isActive(),
                now,
                now);
        return repository.save(providerModelAi);
    }

    @Override
    public ProviderModelAi getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProviderModelAiNotFoundException(id));
    }

    @Override
    public List<ProviderModelAi> getAll() {
        return repository.findAll();
    }

    @Override
    public ProviderModelAi update(UUID id, ProviderModelAiCommand command) {
        ProviderModelAi existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ProviderModelAi updated = new ProviderModelAi(
                existing.id(),
                command.nameProviderAi(),
                command.razonSocial(),
                command.sitioWeb(),
                command.isActive(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ProviderModelAiNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
