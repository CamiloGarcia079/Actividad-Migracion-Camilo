package com.mindconnect.infrastructure.messagetype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.messagetype.model.aggregates.MessageType;
import com.mindconnect.domain.messagetype.repositories.MessageTypeRepository;
import com.mindconnect.infrastructure.messagetype.adapters.out.persistence.mappers.MessageTypePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto MessageTypeRepository del dominio usando JPA.
 */
@Component
public class MessageTypeRepositoryAdapter implements MessageTypeRepository {

    private final SpringDataMessageTypeJpaRepository jpaRepository;
    private final MessageTypePersistenceMapper mapper;

    public MessageTypeRepositoryAdapter(SpringDataMessageTypeJpaRepository jpaRepository,
                                     MessageTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MessageType save(MessageType messageType) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(messageType)));
    }

    @Override
    public Optional<MessageType> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<MessageType> findAll() {
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
