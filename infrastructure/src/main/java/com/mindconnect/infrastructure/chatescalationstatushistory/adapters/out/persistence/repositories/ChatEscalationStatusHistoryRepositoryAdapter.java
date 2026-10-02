package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatescalationstatushistory.model.aggregates.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.repositories.ChatEscalationStatusHistoryRepository;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers.ChatEscalationStatusHistoryPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatEscalationStatusHistoryRepository del dominio usando JPA.
 */
@Component
public class ChatEscalationStatusHistoryRepositoryAdapter implements ChatEscalationStatusHistoryRepository {

    private final SpringDataChatEscalationStatusHistoryJpaRepository jpaRepository;
    private final ChatEscalationStatusHistoryPersistenceMapper mapper;

    public ChatEscalationStatusHistoryRepositoryAdapter(SpringDataChatEscalationStatusHistoryJpaRepository jpaRepository,
                                     ChatEscalationStatusHistoryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalationStatusHistory save(ChatEscalationStatusHistory chatEscalationStatusHistory) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatEscalationStatusHistory)));
    }

    @Override
    public Optional<ChatEscalationStatusHistory> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalationStatusHistory> findAll() {
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
