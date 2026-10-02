package com.mindconnect.application.conversationstatus.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.conversationstatus.dtos.ConversationStatusCommand;
import com.mindconnect.domain.conversationstatus.model.aggregates.ConversationStatus;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre conversations_statuses.
 */
public interface ConversationStatusUseCase {

    ConversationStatus create(ConversationStatusCommand command);

    ConversationStatus getById(UUID id);

    List<ConversationStatus> getAll();

    ConversationStatus update(UUID id, ConversationStatusCommand command);

    void delete(UUID id);
}
