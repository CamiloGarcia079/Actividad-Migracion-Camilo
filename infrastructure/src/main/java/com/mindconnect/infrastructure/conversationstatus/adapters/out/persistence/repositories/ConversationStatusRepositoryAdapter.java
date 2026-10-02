package com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.conversationstatus.model.aggregates.ConversationStatus;
import com.mindconnect.domain.conversationstatus.repositories.ConversationStatusRepository;
import com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.mappers.ConversationStatusPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ConversationStatusRepository del dominio usando JPA.
 */
@Component
public class ConversationStatusRepositoryAdapter implements ConversationStatusRepository {

    private final SpringDataConversationStatusJpaRepository jpaRepository;
    private final ConversationStatusPersistenceMapper mapper;

    public ConversationStatusRepositoryAdapter(SpringDataConversationStatusJpaRepository jpaRepository,
                                     ConversationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ConversationStatus save(ConversationStatus conversationStatus) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(conversationStatus)));
    }

    @Override
    public Optional<ConversationStatus> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ConversationStatus> findAll() {
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
