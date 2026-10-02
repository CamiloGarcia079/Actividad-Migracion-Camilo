package com.mindconnect.infrastructure.treatmentgoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.treatmentgoal.ports.in.TreatmentGoalUseCase;
import com.mindconnect.application.treatmentgoal.usecases.TreatmentGoalService;
import com.mindconnect.domain.treatmentgoal.repositories.TreatmentGoalRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class TreatmentGoalBeansConfig {

    @Bean
    public TreatmentGoalUseCase treatmentGoalUseCase(TreatmentGoalRepository treatmentGoalRepository) {
        return new TreatmentGoalService(treatmentGoalRepository);
    }
}
