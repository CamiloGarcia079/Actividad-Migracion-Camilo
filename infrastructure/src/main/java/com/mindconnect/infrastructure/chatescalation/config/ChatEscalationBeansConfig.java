package com.mindconnect.infrastructure.chatescalation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatescalation.ports.in.ChatEscalationUseCase;
import com.mindconnect.application.chatescalation.usecases.ChatEscalationService;
import com.mindconnect.domain.chatescalation.repositories.ChatEscalationRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatEscalationBeansConfig {

    @Bean
    public ChatEscalationUseCase chatEscalationUseCase(ChatEscalationRepository chatEscalationRepository) {
        return new ChatEscalationService(chatEscalationRepository);
    }
}
