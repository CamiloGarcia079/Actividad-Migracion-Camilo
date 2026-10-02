package com.mindconnect.infrastructure.professionaltype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.professionaltype.ports.in.ProfessionalTypeUseCase;
import com.mindconnect.application.professionaltype.usecases.ProfessionalTypeService;
import com.mindconnect.domain.professionaltype.repositories.ProfessionalTypeRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ProfessionalTypeBeansConfig {

    @Bean
    public ProfessionalTypeUseCase professionalTypeUseCase(ProfessionalTypeRepository professionalTypeRepository) {
        return new ProfessionalTypeService(professionalTypeRepository);
    }
}
