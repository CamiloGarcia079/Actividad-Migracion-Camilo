package com.mindconnect.infrastructure.documenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.documenttype.model.aggregates.DocumentType;
import com.mindconnect.domain.documenttype.repositories.DocumentTypeRepository;
import com.mindconnect.infrastructure.documenttype.adapters.out.persistence.mappers.DocumentTypePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto DocumentTypeRepository del dominio usando JPA.
 */
@Component
public class DocumentTypeRepositoryAdapter implements DocumentTypeRepository {

    private final SpringDataDocumentTypeJpaRepository jpaRepository;
    private final DocumentTypePersistenceMapper mapper;

    public DocumentTypeRepositoryAdapter(SpringDataDocumentTypeJpaRepository jpaRepository,
                                     DocumentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DocumentType save(DocumentType documentType) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(documentType)));
    }

    @Override
    public Optional<DocumentType> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DocumentType> findAll() {
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
