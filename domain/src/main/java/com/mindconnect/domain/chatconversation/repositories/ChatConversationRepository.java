package com.mindconnect.domain.chatconversation.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatconversation.model.aggregates.ChatConversation;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_conversations.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatConversationRepository {

    ChatConversation save(ChatConversation chatConversation);

    Optional<ChatConversation> findById(UUID id);

    List<ChatConversation> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
