package com.mindconnect.infrastructure.encounterstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.encounterstatus.ports.in.EncounterStatusUseCase;
import com.mindconnect.application.encounterstatus.usecases.EncounterStatusService;
import com.mindconnect.domain.encounterstatus.repositories.EncounterStatusRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class EncounterStatusBeansConfig {

    @Bean
    public EncounterStatusUseCase encounterStatusUseCase(EncounterStatusRepository encounterStatusRepository) {
        return new EncounterStatusService(encounterStatusRepository);
    }
}
