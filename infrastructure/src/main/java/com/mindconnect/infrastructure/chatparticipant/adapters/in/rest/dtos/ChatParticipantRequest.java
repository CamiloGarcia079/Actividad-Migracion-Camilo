package com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatparticipant.dtos.ChatParticipantCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_participants.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatParticipantRequest(
        @NotNull UUID conversationId,
        @NotNull UUID participantTypeId,
        UUID patientId,
        UUID professionalId) {

    public ChatParticipantCommand toCommand() {
        return new ChatParticipantCommand(
                conversationId, participantTypeId, patientId, professionalId);
    }
}
