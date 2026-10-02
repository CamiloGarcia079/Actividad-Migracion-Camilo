package com.mindconnect.infrastructure.messagetype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.messagetype.ports.in.MessageTypeUseCase;
import com.mindconnect.application.messagetype.usecases.MessageTypeService;
import com.mindconnect.domain.messagetype.repositories.MessageTypeRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class MessageTypeBeansConfig {

    @Bean
    public MessageTypeUseCase messageTypeUseCase(MessageTypeRepository messageTypeRepository) {
        return new MessageTypeService(messageTypeRepository);
    }
}
