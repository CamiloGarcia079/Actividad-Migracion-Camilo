package com.mindconnect.domain.chatparticipant.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatparticipant.model.aggregates.ChatParticipant;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_participants.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatParticipantRepository {

    ChatParticipant save(ChatParticipant chatParticipant);

    Optional<ChatParticipant> findById(UUID id);

    List<ChatParticipant> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
