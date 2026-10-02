package com.mindconnect.domain.chatescalationstatushistory.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatescalationstatushistory.model.aggregates.ChatEscalationStatusHistory;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_escalation_status_history.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatEscalationStatusHistoryRepository {

    ChatEscalationStatusHistory save(ChatEscalationStatusHistory chatEscalationStatusHistory);

    Optional<ChatEscalationStatusHistory> findById(UUID id);

    List<ChatEscalationStatusHistory> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
