package com.mindconnect.domain.chatairun.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatairun.model.aggregates.ChatAiRun;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_ai_runs.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatAiRunRepository {

    ChatAiRun save(ChatAiRun chatAiRun);

    Optional<ChatAiRun> findById(UUID id);

    List<ChatAiRun> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
