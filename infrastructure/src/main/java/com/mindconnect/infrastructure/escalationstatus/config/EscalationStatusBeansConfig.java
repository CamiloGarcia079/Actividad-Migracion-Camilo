package com.mindconnect.infrastructure.escalationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.escalationstatus.ports.in.EscalationStatusUseCase;
import com.mindconnect.application.escalationstatus.usecases.EscalationStatusService;
import com.mindconnect.domain.escalationstatus.repositories.EscalationStatusRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class EscalationStatusBeansConfig {

    @Bean
    public EscalationStatusUseCase escalationStatusUseCase(EscalationStatusRepository escalationStatusRepository) {
        return new EscalationStatusService(escalationStatusRepository);
    }
}
