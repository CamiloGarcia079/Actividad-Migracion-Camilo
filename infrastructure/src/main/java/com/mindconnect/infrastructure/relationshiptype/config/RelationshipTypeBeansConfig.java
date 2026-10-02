package com.mindconnect.infrastructure.relationshiptype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.relationshiptype.ports.in.RelationshipTypeUseCase;
import com.mindconnect.application.relationshiptype.usecases.RelationshipTypeService;
import com.mindconnect.domain.relationshiptype.repositories.RelationshipTypeRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class RelationshipTypeBeansConfig {

    @Bean
    public RelationshipTypeUseCase relationshipTypeUseCase(RelationshipTypeRepository relationshipTypeRepository) {
        return new RelationshipTypeService(relationshipTypeRepository);
    }
}
