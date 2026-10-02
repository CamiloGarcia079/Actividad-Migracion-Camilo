package com.mindconnect.application.chatescalationstatushistory.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatescalationstatushistory.dtos.ChatEscalationStatusHistoryCommand;
import com.mindconnect.domain.chatescalationstatushistory.model.aggregates.ChatEscalationStatusHistory;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_escalation_status_history.
 */
public interface ChatEscalationStatusHistoryUseCase {

    ChatEscalationStatusHistory create(ChatEscalationStatusHistoryCommand command);

    ChatEscalationStatusHistory getById(UUID id);

    List<ChatEscalationStatusHistory> getAll();

    ChatEscalationStatusHistory update(UUID id, ChatEscalationStatusHistoryCommand command);

    void delete(UUID id);
}
