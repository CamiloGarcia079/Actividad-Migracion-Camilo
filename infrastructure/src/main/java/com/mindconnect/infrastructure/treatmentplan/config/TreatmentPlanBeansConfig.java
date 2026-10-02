package com.mindconnect.infrastructure.treatmentplan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.treatmentplan.ports.in.TreatmentPlanUseCase;
import com.mindconnect.application.treatmentplan.usecases.TreatmentPlanService;
import com.mindconnect.domain.treatmentplan.repositories.TreatmentPlanRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class TreatmentPlanBeansConfig {

    @Bean
    public TreatmentPlanUseCase treatmentPlanUseCase(TreatmentPlanRepository treatmentPlanRepository) {
        return new TreatmentPlanService(treatmentPlanRepository);
    }
}
