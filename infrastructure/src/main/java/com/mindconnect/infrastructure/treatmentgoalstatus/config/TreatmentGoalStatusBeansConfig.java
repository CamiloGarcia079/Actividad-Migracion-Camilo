package com.mindconnect.infrastructure.treatmentgoalstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.treatmentgoalstatus.ports.in.TreatmentGoalStatusUseCase;
import com.mindconnect.application.treatmentgoalstatus.usecases.TreatmentGoalStatusService;
import com.mindconnect.domain.treatmentgoalstatus.repositories.TreatmentGoalStatusRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class TreatmentGoalStatusBeansConfig {

    @Bean
    public TreatmentGoalStatusUseCase treatmentGoalStatusUseCase(TreatmentGoalStatusRepository treatmentGoalStatusRepository) {
        return new TreatmentGoalStatusService(treatmentGoalStatusRepository);
    }
}
