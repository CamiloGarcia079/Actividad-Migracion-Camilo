package com.mindconnect.infrastructure.chatairunmetric.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatairunmetric.ports.in.ChatAiRunMetricUseCase;
import com.mindconnect.application.chatairunmetric.usecases.ChatAiRunMetricService;
import com.mindconnect.domain.chatairunmetric.repositories.ChatAiRunMetricRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatAiRunMetricBeansConfig {

    @Bean
    public ChatAiRunMetricUseCase chatAiRunMetricUseCase(ChatAiRunMetricRepository chatAiRunMetricRepository) {
        return new ChatAiRunMetricService(chatAiRunMetricRepository);
    }
}
