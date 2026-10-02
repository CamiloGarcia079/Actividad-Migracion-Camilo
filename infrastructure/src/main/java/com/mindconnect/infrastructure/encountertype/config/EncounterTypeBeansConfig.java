package com.mindconnect.infrastructure.encountertype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.encountertype.ports.in.EncounterTypeUseCase;
import com.mindconnect.application.encountertype.usecases.EncounterTypeService;
import com.mindconnect.domain.encountertype.repositories.EncounterTypeRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class EncounterTypeBeansConfig {

    @Bean
    public EncounterTypeUseCase encounterTypeUseCase(EncounterTypeRepository encounterTypeRepository) {
        return new EncounterTypeService(encounterTypeRepository);
    }
}
