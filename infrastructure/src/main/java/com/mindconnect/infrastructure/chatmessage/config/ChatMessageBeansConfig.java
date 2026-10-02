package com.mindconnect.infrastructure.chatmessage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatmessage.ports.in.ChatMessageUseCase;
import com.mindconnect.application.chatmessage.usecases.ChatMessageService;
import com.mindconnect.domain.chatmessage.repositories.ChatMessageRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatMessageBeansConfig {

    @Bean
    public ChatMessageUseCase chatMessageUseCase(ChatMessageRepository chatMessageRepository) {
        return new ChatMessageService(chatMessageRepository);
    }
}
