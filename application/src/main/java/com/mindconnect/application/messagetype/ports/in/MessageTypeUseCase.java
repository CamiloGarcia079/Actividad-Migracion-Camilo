package com.mindconnect.application.messagetype.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.messagetype.dtos.MessageTypeCommand;
import com.mindconnect.domain.messagetype.model.aggregates.MessageType;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre message_types.
 */
public interface MessageTypeUseCase {

    MessageType create(MessageTypeCommand command);

    MessageType getById(UUID id);

    List<MessageType> getAll();

    MessageType update(UUID id, MessageTypeCommand command);

    void delete(UUID id);
}
