package com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatescalation.model.aggregates.ChatEscalation;
import com.mindconnect.domain.chatescalation.repositories.ChatEscalationRepository;
import com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.mappers.ChatEscalationPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatEscalationRepository del dominio usando JPA.
 */
@Component
public class ChatEscalationRepositoryAdapter implements ChatEscalationRepository {

    private final SpringDataChatEscalationJpaRepository jpaRepository;
    private final ChatEscalationPersistenceMapper mapper;

    public ChatEscalationRepositoryAdapter(SpringDataChatEscalationJpaRepository jpaRepository,
                                     ChatEscalationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalation save(ChatEscalation chatEscalation) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatEscalation)));
    }

    @Override
    public Optional<ChatEscalation> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalation> findAll() {
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
