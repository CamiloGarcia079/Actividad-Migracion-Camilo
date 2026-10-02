package com.mindconnect.infrastructure.consenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.consenttype.ports.in.ConsentTypeUseCase;
import com.mindconnect.application.consenttype.usecases.ConsentTypeService;
import com.mindconnect.domain.consenttype.repositories.ConsentTypeRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ConsentTypeBeansConfig {

    @Bean
    public ConsentTypeUseCase consentTypeUseCase(ConsentTypeRepository consentTypeRepository) {
        return new ConsentTypeService(consentTypeRepository);
    }
}
