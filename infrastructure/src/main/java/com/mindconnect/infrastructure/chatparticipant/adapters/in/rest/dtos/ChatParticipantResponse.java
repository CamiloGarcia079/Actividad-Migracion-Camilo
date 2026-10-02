package com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatparticipant.model.aggregates.ChatParticipant;

/**
 * Respuesta JSON de la API para un registro de chat_participants.
 */
public record ChatParticipantResponse(
        UUID id,
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ChatParticipantResponse from(ChatParticipant chatParticipant) {
        return new ChatParticipantResponse(
                chatParticipant.id(), chatParticipant.conversationId(), chatParticipant.participantTypeId(), chatParticipant.patientId(), chatParticipant.professionalId(), chatParticipant.createdAt(), chatParticipant.updatedAt());
    }
}
