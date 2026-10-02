package com.mindconnect.application.priority.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.priority.dtos.PriorityCommand;
import com.mindconnect.domain.priority.model.aggregates.Priority;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre priorities.
 */
public interface PriorityUseCase {

    Priority create(PriorityCommand command);

    Priority getById(UUID id);

    List<Priority> getAll();

    Priority update(UUID id, PriorityCommand command);

    void delete(UUID id);
}
