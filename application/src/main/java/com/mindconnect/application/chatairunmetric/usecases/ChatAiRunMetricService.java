package com.mindconnect.application.chatairunmetric.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatairunmetric.dtos.ChatAiRunMetricCommand;
import com.mindconnect.application.chatairunmetric.exceptions.ChatAiRunMetricNotFoundException;
import com.mindconnect.application.chatairunmetric.ports.in.ChatAiRunMetricUseCase;
import com.mindconnect.domain.chatairunmetric.model.aggregates.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.repositories.ChatAiRunMetricRepository;

/**
 * Implementación de los casos de uso de chat_ai_run_metrics.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatAiRunMetricService implements ChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository repository;

    public ChatAiRunMetricService(ChatAiRunMetricRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatAiRunMetric create(ChatAiRunMetricCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatAiRunMetric chatAiRunMetric = new ChatAiRunMetric(
                UUID.randomUUID(),
                command.aiRunId(),
                command.promptTokens(),
                command.completionTokens(),
                command.totalTokens(),
                command.cost(),
                now);
        return repository.save(chatAiRunMetric);
    }

    @Override
    public ChatAiRunMetric getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatAiRunMetricNotFoundException(id));
    }

    @Override
    public List<ChatAiRunMetric> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatAiRunMetric update(UUID id, ChatAiRunMetricCommand command) {
        ChatAiRunMetric existing = getById(id);
        ChatAiRunMetric updated = new ChatAiRunMetric(
                existing.id(),
                command.aiRunId(),
                command.promptTokens(),
                command.completionTokens(),
                command.totalTokens(),
                command.cost(),
                existing.createdAt());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatAiRunMetricNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
