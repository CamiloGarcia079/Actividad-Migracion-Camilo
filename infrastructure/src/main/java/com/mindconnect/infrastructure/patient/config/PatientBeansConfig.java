package com.mindconnect.infrastructure.patient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.patient.ports.in.PatientUseCase;
import com.mindconnect.application.patient.usecases.PatientService;
import com.mindconnect.domain.patient.repositories.PatientRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class PatientBeansConfig {

    @Bean
    public PatientUseCase patientUseCase(PatientRepository patientRepository) {
        return new PatientService(patientRepository);
    }
}
