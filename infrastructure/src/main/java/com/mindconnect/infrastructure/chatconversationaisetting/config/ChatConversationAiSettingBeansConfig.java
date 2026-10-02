package com.mindconnect.infrastructure.chatconversationaisetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatconversationaisetting.ports.in.ChatConversationAiSettingUseCase;
import com.mindconnect.application.chatconversationaisetting.usecases.ChatConversationAiSettingService;
import com.mindconnect.domain.chatconversationaisetting.repositories.ChatConversationAiSettingRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ChatConversationAiSettingBeansConfig {

    @Bean
    public ChatConversationAiSettingUseCase chatConversationAiSettingUseCase(ChatConversationAiSettingRepository chatConversationAiSettingRepository) {
        return new ChatConversationAiSettingService(chatConversationAiSettingRepository);
    }
}
