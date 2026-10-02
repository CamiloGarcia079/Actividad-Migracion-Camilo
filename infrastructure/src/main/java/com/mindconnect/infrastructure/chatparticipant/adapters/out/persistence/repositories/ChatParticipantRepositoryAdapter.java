package com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatparticipant.model.aggregates.ChatParticipant;
import com.mindconnect.domain.chatparticipant.repositories.ChatParticipantRepository;
import com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.mappers.ChatParticipantPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatParticipantRepository del dominio usando JPA.
 */
@Component
public class ChatParticipantRepositoryAdapter implements ChatParticipantRepository {

    private final SpringDataChatParticipantJpaRepository jpaRepository;
    private final ChatParticipantPersistenceMapper mapper;

    public ChatParticipantRepositoryAdapter(SpringDataChatParticipantJpaRepository jpaRepository,
                                     ChatParticipantPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatParticipant save(ChatParticipant chatParticipant) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatParticipant)));
    }

    @Override
    public Optional<ChatParticipant> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatParticipant> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
