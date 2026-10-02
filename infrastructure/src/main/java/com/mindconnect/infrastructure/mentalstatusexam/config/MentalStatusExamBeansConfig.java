package com.mindconnect.infrastructure.mentalstatusexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.mentalstatusexam.ports.in.MentalStatusExamUseCase;
import com.mindconnect.application.mentalstatusexam.usecases.MentalStatusExamService;
import com.mindconnect.domain.mentalstatusexam.repositories.MentalStatusExamRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class MentalStatusExamBeansConfig {

    @Bean
    public MentalStatusExamUseCase mentalStatusExamUseCase(MentalStatusExamRepository mentalStatusExamRepository) {
        return new MentalStatusExamService(mentalStatusExamRepository);
    }
}
