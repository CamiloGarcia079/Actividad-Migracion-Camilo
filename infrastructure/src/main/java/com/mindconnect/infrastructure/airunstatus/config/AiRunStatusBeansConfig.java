package com.mindconnect.infrastructure.airunstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.airunstatus.ports.in.AiRunStatusUseCase;
import com.mindconnect.application.airunstatus.usecases.AiRunStatusService;
import com.mindconnect.domain.airunstatus.repositories.AiRunStatusRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class AiRunStatusBeansConfig {

    @Bean
    public AiRunStatusUseCase aiRunStatusUseCase(AiRunStatusRepository aiRunStatusRepository) {
        return new AiRunStatusService(aiRunStatusRepository);
    }
}
