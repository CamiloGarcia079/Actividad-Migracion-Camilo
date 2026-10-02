package com.mindconnect.infrastructure.chatescalationstatushistory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatescalationstatushistory.ports.in.ChatEscalationStatusHistoryUseCase;
import com.mindconnect.application.chatescalationstatushistory.usecases.ChatEscalationStatusHistoryService;
import com.mindconnect.domain.chatescalationstatushistory.repositories.ChatEscalationStatusHistoryRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatEscalationStatusHistoryBeansConfig {

    @Bean
    public ChatEscalationStatusHistoryUseCase chatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository chatEscalationStatusHistoryRepository) {
        return new ChatEscalationStatusHistoryService(chatEscalationStatusHistoryRepository);
    }
}
