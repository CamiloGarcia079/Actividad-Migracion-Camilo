package com.mindconnect.domain.chatairunerror.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatairunerror.model.aggregates.ChatAiRunError;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_ai_run_errors.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatAiRunErrorRepository {

    ChatAiRunError save(ChatAiRunError chatAiRunError);

    Optional<ChatAiRunError> findById(UUID id);

    List<ChatAiRunError> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
