package com.mindconnect.infrastructure.aimodel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.aimodel.ports.in.AiModelUseCase;
import com.mindconnect.application.aimodel.usecases.AiModelService;
import com.mindconnect.domain.aimodel.repositories.AiModelRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class AiModelBeansConfig {

    @Bean
    public AiModelUseCase aiModelUseCase(AiModelRepository aiModelRepository) {
        return new AiModelService(aiModelRepository);
    }
}
