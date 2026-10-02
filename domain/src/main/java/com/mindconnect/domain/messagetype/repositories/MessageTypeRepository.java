package com.mindconnect.domain.messagetype.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.messagetype.model.aggregates.MessageType;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar message_types.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface MessageTypeRepository {

    MessageType save(MessageType messageType);

    Optional<MessageType> findById(UUID id);

    List<MessageType> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
