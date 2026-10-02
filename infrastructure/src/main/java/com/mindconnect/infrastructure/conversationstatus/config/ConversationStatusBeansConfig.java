package com.mindconnect.infrastructure.conversationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.conversationstatus.ports.in.ConversationStatusUseCase;
import com.mindconnect.application.conversationstatus.usecases.ConversationStatusService;
import com.mindconnect.domain.conversationstatus.repositories.ConversationStatusRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ConversationStatusBeansConfig {

    @Bean
    public ConversationStatusUseCase conversationStatusUseCase(ConversationStatusRepository conversationStatusRepository) {
        return new ConversationStatusService(conversationStatusRepository);
    }
}
