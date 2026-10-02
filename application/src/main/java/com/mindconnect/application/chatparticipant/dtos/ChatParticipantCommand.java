package com.mindconnect.application.chatparticipant.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_participants.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatParticipantCommand(
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId) {
}
