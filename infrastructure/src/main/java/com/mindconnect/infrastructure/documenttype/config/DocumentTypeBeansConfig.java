package com.mindconnect.infrastructure.documenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.documenttype.ports.in.DocumentTypeUseCase;
import com.mindconnect.application.documenttype.usecases.DocumentTypeService;
import com.mindconnect.domain.documenttype.repositories.DocumentTypeRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class DocumentTypeBeansConfig {

    @Bean
    public DocumentTypeUseCase documentTypeUseCase(DocumentTypeRepository documentTypeRepository) {
        return new DocumentTypeService(documentTypeRepository);
    }
}
