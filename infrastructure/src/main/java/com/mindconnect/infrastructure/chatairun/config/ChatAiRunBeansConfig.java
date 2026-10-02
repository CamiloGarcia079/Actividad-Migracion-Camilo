package com.mindconnect.infrastructure.chatairun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatairun.ports.in.ChatAiRunUseCase;
import com.mindconnect.application.chatairun.usecases.ChatAiRunService;
import com.mindconnect.domain.chatairun.repositories.ChatAiRunRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatAiRunBeansConfig {

    @Bean
    public ChatAiRunUseCase chatAiRunUseCase(ChatAiRunRepository chatAiRunRepository) {
        return new ChatAiRunService(chatAiRunRepository);
    }
}
