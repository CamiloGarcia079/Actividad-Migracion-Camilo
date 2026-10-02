package com.mindconnect.infrastructure.encountermodality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.encountermodality.ports.in.EncounterModalityUseCase;
import com.mindconnect.application.encountermodality.usecases.EncounterModalityService;
import com.mindconnect.domain.encountermodality.repositories.EncounterModalityRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class EncounterModalityBeansConfig {

    @Bean
    public EncounterModalityUseCase encounterModalityUseCase(EncounterModalityRepository encounterModalityRepository) {
        return new EncounterModalityService(encounterModalityRepository);
    }
}
