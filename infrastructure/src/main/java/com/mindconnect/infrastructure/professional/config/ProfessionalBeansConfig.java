package com.mindconnect.infrastructure.professional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.professional.ports.in.ProfessionalUseCase;
import com.mindconnect.application.professional.usecases.ProfessionalService;
import com.mindconnect.domain.professional.repositories.ProfessionalRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ProfessionalBeansConfig {

    @Bean
    public ProfessionalUseCase professionalUseCase(ProfessionalRepository professionalRepository) {
        return new ProfessionalService(professionalRepository);
    }
}
