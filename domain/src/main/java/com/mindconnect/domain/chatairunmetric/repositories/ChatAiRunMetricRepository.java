package com.mindconnect.domain.chatairunmetric.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatairunmetric.model.aggregates.ChatAiRunMetric;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_ai_run_metrics.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatAiRunMetricRepository {

    ChatAiRunMetric save(ChatAiRunMetric chatAiRunMetric);

    Optional<ChatAiRunMetric> findById(UUID id);

    List<ChatAiRunMetric> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
