package com.mindconnect.application.chatparticipant.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatparticipant.dtos.ChatParticipantCommand;
import com.mindconnect.domain.chatparticipant.model.aggregates.ChatParticipant;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_participants.
 */
public interface ChatParticipantUseCase {

    ChatParticipant create(ChatParticipantCommand command);

    ChatParticipant getById(UUID id);

    List<ChatParticipant> getAll();

    ChatParticipant update(UUID id, ChatParticipantCommand command);

    void delete(UUID id);
}
