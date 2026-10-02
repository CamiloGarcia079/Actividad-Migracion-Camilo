package com.mindconnect.infrastructure.clinicalrecord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.clinicalrecord.ports.in.ClinicalRecordUseCase;
import com.mindconnect.application.clinicalrecord.usecases.ClinicalRecordService;
import com.mindconnect.domain.clinicalrecord.repositories.ClinicalRecordRepository;

/**
 * Conecta el caso de uso con su repositorio. El servicio de aplicación no usa Spring,
 * así que aquí se crea y se registra como bean.
 */
@Configuration
public class ClinicalRecordBeansConfig {

    @Bean
    public ClinicalRecordUseCase clinicalRecordUseCase(ClinicalRecordRepository clinicalRecordRepository) {
        return new ClinicalRecordService(clinicalRecordRepository);
    }
}
