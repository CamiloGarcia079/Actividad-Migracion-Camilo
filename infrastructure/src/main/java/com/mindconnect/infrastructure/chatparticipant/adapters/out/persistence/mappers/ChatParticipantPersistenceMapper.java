package com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatparticipant.model.aggregates.ChatParticipant;
import com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_participants.
 */
@Component
public class ChatParticipantPersistenceMapper {

    public ChatParticipantJpaEntity toEntity(ChatParticipant chatParticipant) {
        ChatParticipantJpaEntity entity = new ChatParticipantJpaEntity();
        entity.setId(chatParticipant.id());
        entity.setConversationId(chatParticipant.conversationId());
        entity.setParticipantTypeId(chatParticipant.participantTypeId());
        entity.setPatientId(chatParticipant.patientId());
        entity.setProfessionalId(chatParticipant.professionalId());
        entity.setCreatedAt(chatParticipant.createdAt());
        entity.setUpdatedAt(chatParticipant.updatedAt());
        return entity;
    }

    public ChatParticipant toDomain(ChatParticipantJpaEntity entity) {
        return new ChatParticipant(
                entity.getId(),
                entity.getConversationId(),
                entity.getParticipantTypeId(),
                entity.getPatientId(),
                entity.getProfessionalId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
