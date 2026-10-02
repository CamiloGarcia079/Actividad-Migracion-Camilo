package com.mindconnect.infrastructure.treatmentstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.treatmentstatus.ports.in.TreatmentStatusUseCase;
import com.mindconnect.application.treatmentstatus.usecases.TreatmentStatusService;
import com.mindconnect.domain.treatmentstatus.repositories.TreatmentStatusRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class TreatmentStatusBeansConfig {

    @Bean
    public TreatmentStatusUseCase treatmentStatusUseCase(TreatmentStatusRepository treatmentStatusRepository) {
        return new TreatmentStatusService(treatmentStatusRepository);
    }
}
