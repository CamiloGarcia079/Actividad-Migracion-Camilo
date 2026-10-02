package com.mindconnect.domain.chatmessage.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatmessage.model.aggregates.ChatMessage;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_messages.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatMessageRepository {

    ChatMessage save(ChatMessage chatMessage);

    Optional<ChatMessage> findById(UUID id);

    List<ChatMessage> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
