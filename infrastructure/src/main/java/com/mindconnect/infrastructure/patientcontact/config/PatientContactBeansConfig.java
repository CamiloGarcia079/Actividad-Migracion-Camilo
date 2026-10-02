package com.mindconnect.infrastructure.patientcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.patientcontact.ports.in.PatientContactUseCase;
import com.mindconnect.application.patientcontact.usecases.PatientContactService;
import com.mindconnect.domain.patientcontact.repositories.PatientContactRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class PatientContactBeansConfig {

    @Bean
    public PatientContactUseCase patientContactUseCase(PatientContactRepository patientContactRepository) {
        return new PatientContactService(patientContactRepository);
    }
}
