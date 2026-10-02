package com.mindconnect.application.sendertype.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.sendertype.dtos.SenderTypeCommand;
import com.mindconnect.domain.sendertype.model.aggregates.SenderType;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre sender_types.
 */
public interface SenderTypeUseCase {

    SenderType create(SenderTypeCommand command);

    SenderType getById(UUID id);

    List<SenderType> getAll();

    SenderType update(UUID id, SenderTypeCommand command);

    void delete(UUID id);
}
