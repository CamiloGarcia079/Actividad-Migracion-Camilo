package com.mindconnect.infrastructure.chatparticipant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatparticipant.ports.in.ChatParticipantUseCase;
import com.mindconnect.application.chatparticipant.usecases.ChatParticipantService;
import com.mindconnect.domain.chatparticipant.repositories.ChatParticipantRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatParticipantBeansConfig {

    @Bean
    public ChatParticipantUseCase chatParticipantUseCase(ChatParticipantRepository chatParticipantRepository) {
        return new ChatParticipantService(chatParticipantRepository);
    }
}
