package com.mindconnect.application.airunstatus.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.airunstatus.dtos.AiRunStatusCommand;
import com.mindconnect.domain.airunstatus.model.aggregates.AiRunStatus;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre ai_runs_statuses.
 */
public interface AiRunStatusUseCase {

    AiRunStatus create(AiRunStatusCommand command);

    AiRunStatus getById(UUID id);

    List<AiRunStatus> getAll();

    AiRunStatus update(UUID id, AiRunStatusCommand command);

    void delete(UUID id);
}
