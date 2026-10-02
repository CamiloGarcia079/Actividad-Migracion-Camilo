package com.mindconnect.application.chatairunmetric.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatairunmetric.dtos.ChatAiRunMetricCommand;
import com.mindconnect.domain.chatairunmetric.model.aggregates.ChatAiRunMetric;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_ai_run_metrics.
 */
public interface ChatAiRunMetricUseCase {

    ChatAiRunMetric create(ChatAiRunMetricCommand command);

    ChatAiRunMetric getById(UUID id);

    List<ChatAiRunMetric> getAll();

    ChatAiRunMetric update(UUID id, ChatAiRunMetricCommand command);

    void delete(UUID id);
}
