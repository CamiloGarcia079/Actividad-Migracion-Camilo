package com.mindconnect.infrastructure.gender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.gender.ports.in.GenderUseCase;
import com.mindconnect.application.gender.usecases.GenderService;
import com.mindconnect.domain.gender.repositories.GenderRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class GenderBeansConfig {

    @Bean
    public GenderUseCase genderUseCase(GenderRepository genderRepository) {
        return new GenderService(genderRepository);
    }
}
