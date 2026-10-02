package com.mindconnect.infrastructure.patientallergy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.patientallergy.ports.in.PatientAllergyUseCase;
import com.mindconnect.application.patientallergy.usecases.PatientAllergyService;
import com.mindconnect.domain.patientallergy.repositories.PatientAllergyRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class PatientAllergyBeansConfig {

    @Bean
    public PatientAllergyUseCase patientAllergyUseCase(PatientAllergyRepository patientAllergyRepository) {
        return new PatientAllergyService(patientAllergyRepository);
    }
}
