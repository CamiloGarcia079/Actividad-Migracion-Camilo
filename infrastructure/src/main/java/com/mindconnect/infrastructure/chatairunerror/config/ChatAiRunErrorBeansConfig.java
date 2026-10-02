package com.mindconnect.infrastructure.chatairunerror.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatairunerror.ports.in.ChatAiRunErrorUseCase;
import com.mindconnect.application.chatairunerror.usecases.ChatAiRunErrorService;
import com.mindconnect.domain.chatairunerror.repositories.ChatAiRunErrorRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatAiRunErrorBeansConfig {

    @Bean
    public ChatAiRunErrorUseCase chatAiRunErrorUseCase(ChatAiRunErrorRepository chatAiRunErrorRepository) {
        return new ChatAiRunErrorService(chatAiRunErrorRepository);
    }
}
