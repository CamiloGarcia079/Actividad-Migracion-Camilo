package com.mindconnect.infrastructure.chatconversation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatconversation.ports.in.ChatConversationUseCase;
import com.mindconnect.application.chatconversation.usecases.ChatConversationService;
import com.mindconnect.domain.chatconversation.repositories.ChatConversationRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatConversationBeansConfig {

    @Bean
    public ChatConversationUseCase chatConversationUseCase(ChatConversationRepository chatConversationRepository) {
        return new ChatConversationService(chatConversationRepository);
    }
}
