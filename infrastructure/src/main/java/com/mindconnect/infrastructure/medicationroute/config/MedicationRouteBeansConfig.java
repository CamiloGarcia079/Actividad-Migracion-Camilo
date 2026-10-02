package com.mindconnect.infrastructure.medicationroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.medicationroute.ports.in.MedicationRouteUseCase;
import com.mindconnect.application.medicationroute.usecases.MedicationRouteService;
import com.mindconnect.domain.medicationroute.repositories.MedicationRouteRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class MedicationRouteBeansConfig {

    @Bean
    public MedicationRouteUseCase medicationRouteUseCase(MedicationRouteRepository medicationRouteRepository) {
        return new MedicationRouteService(medicationRouteRepository);
    }
}
