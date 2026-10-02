package com.mindconnect.infrastructure.clinicalrecordstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.clinicalrecordstatus.ports.in.ClinicalRecordStatusUseCase;
import com.mindconnect.application.clinicalrecordstatus.usecases.ClinicalRecordStatusService;
import com.mindconnect.domain.clinicalrecordstatus.repositories.ClinicalRecordStatusRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ClinicalRecordStatusBeansConfig {

    @Bean
    public ClinicalRecordStatusUseCase clinicalRecordStatusUseCase(ClinicalRecordStatusRepository clinicalRecordStatusRepository) {
        return new ClinicalRecordStatusService(clinicalRecordStatusRepository);
    }
}
