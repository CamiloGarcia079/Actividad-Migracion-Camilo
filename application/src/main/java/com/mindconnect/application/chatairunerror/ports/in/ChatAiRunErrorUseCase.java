package com.mindconnect.application.chatairunerror.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatairunerror.dtos.ChatAiRunErrorCommand;
import com.mindconnect.domain.chatairunerror.model.aggregates.ChatAiRunError;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_ai_run_errors.
 */
public interface ChatAiRunErrorUseCase {

    ChatAiRunError create(ChatAiRunErrorCommand command);

    ChatAiRunError getById(UUID id);

    List<ChatAiRunError> getAll();

    ChatAiRunError update(UUID id, ChatAiRunErrorCommand command);

    void delete(UUID id);
}
