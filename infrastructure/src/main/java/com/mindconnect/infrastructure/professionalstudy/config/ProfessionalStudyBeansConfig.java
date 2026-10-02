package com.mindconnect.infrastructure.professionalstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.professionalstudy.ports.in.ProfessionalStudyUseCase;
import com.mindconnect.application.professionalstudy.usecases.ProfessionalStudyService;
import com.mindconnect.domain.professionalstudy.repositories.ProfessionalStudyRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ProfessionalStudyBeansConfig {

    @Bean
    public ProfessionalStudyUseCase professionalStudyUseCase(ProfessionalStudyRepository professionalStudyRepository) {
        return new ProfessionalStudyService(professionalStudyRepository);
    }
}
