package com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatescalationassignment.model.aggregates.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.repositories.ChatEscalationAssignmentRepository;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.mappers.ChatEscalationAssignmentPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatEscalationAssignmentRepository del dominio usando JPA.
 */
@Component
public class ChatEscalationAssignmentRepositoryAdapter implements ChatEscalationAssignmentRepository {

    private final SpringDataChatEscalationAssignmentJpaRepository jpaRepository;
    private final ChatEscalationAssignmentPersistenceMapper mapper;

    public ChatEscalationAssignmentRepositoryAdapter(SpringDataChatEscalationAssignmentJpaRepository jpaRepository,
                                     ChatEscalationAssignmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalationAssignment save(ChatEscalationAssignment chatEscalationAssignment) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatEscalationAssignment)));
    }

    @Override
    public Optional<ChatEscalationAssignment> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalationAssignment> findAll() {
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
