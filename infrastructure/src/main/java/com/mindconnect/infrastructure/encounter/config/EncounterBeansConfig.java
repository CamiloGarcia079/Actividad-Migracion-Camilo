package com.mindconnect.infrastructure.encounter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.encounter.ports.in.EncounterUseCase;
import com.mindconnect.application.encounter.usecases.EncounterService;
import com.mindconnect.domain.encounter.repositories.EncounterRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class EncounterBeansConfig {

    @Bean
    public EncounterUseCase encounterUseCase(EncounterRepository encounterRepository) {
        return new EncounterService(encounterRepository);
    }
}
