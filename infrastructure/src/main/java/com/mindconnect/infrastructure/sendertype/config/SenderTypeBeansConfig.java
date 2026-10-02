package com.mindconnect.infrastructure.sendertype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.sendertype.ports.in.SenderTypeUseCase;
import com.mindconnect.application.sendertype.usecases.SenderTypeService;
import com.mindconnect.domain.sendertype.repositories.SenderTypeRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class SenderTypeBeansConfig {

    @Bean
    public SenderTypeUseCase senderTypeUseCase(SenderTypeRepository senderTypeRepository) {
        return new SenderTypeService(senderTypeRepository);
    }
}
