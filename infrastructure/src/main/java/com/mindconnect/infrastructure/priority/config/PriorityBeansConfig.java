package com.mindconnect.infrastructure.priority.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.priority.ports.in.PriorityUseCase;
import com.mindconnect.application.priority.usecases.PriorityService;
import com.mindconnect.domain.priority.repositories.PriorityRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class PriorityBeansConfig {

    @Bean
    public PriorityUseCase priorityUseCase(PriorityRepository priorityRepository) {
        return new PriorityService(priorityRepository);
    }
}
