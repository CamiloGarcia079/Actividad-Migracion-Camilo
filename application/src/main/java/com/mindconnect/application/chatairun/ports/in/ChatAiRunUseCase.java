package com.mindconnect.application.chatairun.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatairun.dtos.ChatAiRunCommand;
import com.mindconnect.domain.chatairun.model.aggregates.ChatAiRun;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_ai_runs.
 */
public interface ChatAiRunUseCase {

    ChatAiRun create(ChatAiRunCommand command);

    ChatAiRun getById(UUID id);

    List<ChatAiRun> getAll();

    ChatAiRun update(UUID id, ChatAiRunCommand command);

    void delete(UUID id);
}
