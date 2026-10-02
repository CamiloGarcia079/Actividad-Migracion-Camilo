package com.mindconnect.domain.chatparticipant.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_participants.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatParticipant(
        UUID id,
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ChatParticipant {
        DomainValidations.required(id, "id");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(participantTypeId, "participantTypeId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
