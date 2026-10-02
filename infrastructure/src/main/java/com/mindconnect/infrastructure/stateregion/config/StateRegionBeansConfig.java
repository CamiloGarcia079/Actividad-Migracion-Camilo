package com.mindconnect.infrastructure.stateregion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.stateregion.ports.in.StateRegionUseCase;
import com.mindconnect.application.stateregion.usecases.StateRegionService;
import com.mindconnect.domain.stateregion.repositories.StateRegionRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class StateRegionBeansConfig {

    @Bean
    public StateRegionUseCase stateRegionUseCase(StateRegionRepository stateRegionRepository) {
        return new StateRegionService(stateRegionRepository);
    }
}
