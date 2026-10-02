package com.mindconnect.domain.chatescalation.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatescalation.model.aggregates.ChatEscalation;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_escalations.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatEscalationRepository {

    ChatEscalation save(ChatEscalation chatEscalation);

    Optional<ChatEscalation> findById(UUID id);

    List<ChatEscalation> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
