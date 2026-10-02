package com.mindconnect.domain.conversationstatus.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.conversationstatus.model.aggregates.ConversationStatus;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar conversations_statuses.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ConversationStatusRepository {

    ConversationStatus save(ConversationStatus conversationStatus);

    Optional<ConversationStatus> findById(UUID id);

    List<ConversationStatus> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
