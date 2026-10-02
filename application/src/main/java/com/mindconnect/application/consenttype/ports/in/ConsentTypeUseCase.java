package com.mindconnect.application.consenttype.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.consenttype.dtos.ConsentTypeCommand;
import com.mindconnect.domain.consenttype.model.aggregates.ConsentType;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre consent_types.
 */
public interface ConsentTypeUseCase {

    ConsentType create(ConsentTypeCommand command);

    ConsentType getById(UUID id);

    List<ConsentType> getAll();

    ConsentType update(UUID id, ConsentTypeCommand command);

    void delete(UUID id);
}
